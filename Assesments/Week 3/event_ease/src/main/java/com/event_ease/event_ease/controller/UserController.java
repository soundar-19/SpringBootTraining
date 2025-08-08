package com.event_ease.event_ease.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.event_ease.event_ease.domain.User;
import com.event_ease.event_ease.dto.UserRequestDTO;
import com.event_ease.event_ease.dto.UserResponseDTO;
import com.event_ease.event_ease.exception.InvalidInputException;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.mapper.UserMapper;
import com.event_ease.event_ease.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> response = userService.getAllUsers()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequest) {
        User createdUser = userService.createUser(userMapper.toEntity(userRequest));
        if (createdUser == null) {
            throw new InvalidInputException("Invalid Input");
        }
        return ResponseEntity.ok(userMapper.toDTO(createdUser));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest) {
        User updatedUser = userService.updateUser(id, userMapper.toEntity(userRequest));
        if (updatedUser == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}