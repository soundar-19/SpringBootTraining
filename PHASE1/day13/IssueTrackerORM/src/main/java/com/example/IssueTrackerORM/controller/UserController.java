package com.example.IssueTrackerORM.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.dto.UserRequestDTO;
import com.example.IssueTrackerORM.dto.UserResponseDTO;
import com.example.IssueTrackerORM.exception.InvalidInputException;
import com.example.IssueTrackerORM.exception.ResourceNotFoundException;
import com.example.IssueTrackerORM.mapper.UserMapper;
import com.example.IssueTrackerORM.service.UserService;
import com.example.IssueTrackerORM.service.BugService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private BugService bugService;

    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> response = new ArrayList<>();
        for(User user : userService.getAllUsers()){
            response.add(UserMapper.toDTO(user));
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid id");
        }
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("No such User Found");
        }
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable("name") String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Name Cannot be Empty");
        User user = userService.getUserByName(name);
        if (user == null) throw new ResourceNotFoundException("No such User Found");
        
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }


    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByRole(@PathVariable("role") String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new InvalidInputException("Role is required");
        }
        List<UserResponseDTO> response = new ArrayList<>();
        for(User user : userService.getUsersByRole(role)){
            response.add(UserMapper.toDTO(user));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/role/{role}")
    public ResponseEntity<Long> countByRole(@PathVariable("role") String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new InvalidInputException("Role is required");
        }
        return ResponseEntity.ok(userService.countByRole(role));
    }

    @GetMapping("/with-bugs")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithAssignedBugs() {
        List<UserResponseDTO> response = new ArrayList<>();
        for(User user : userService.getUsersWithAssignedBugs()){
            response.add(UserMapper.toDTO(user));
        }   
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {

            User createdUser = userService.createUser(UserMapper.toEntity(user));
            return ResponseEntity.ok(UserMapper.toDTO(createdUser));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid user ID");
        }
            User user = UserMapper.toEntity(userRequestDTO);
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            if (updatedUser == null) {
                throw new ResourceNotFoundException("No such User Found");
            }
            return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    @PutMapping("/{userId}/assign-bug/{bugId}")
    public ResponseEntity<String> assignBugToUser(@PathVariable Long userId, @PathVariable Long bugId) {
        if (userId == null || userId <= 0) {
            throw new InvalidInputException("Invalid user ID");
        }
        if (bugId == null || bugId <= 0) {
            throw new InvalidInputException("Invalid bug ID");
        }
        
        bugService.assignBugToUser(bugId, userId);
        return ResponseEntity.ok("Bug assigned successfully to user");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            throw new InvalidInputException("No Such User Found");
        }
            userService.deleteUser(id);
            return ResponseEntity.ok("User With ID "+id+" Deleted Successfully!");
    }
    
}