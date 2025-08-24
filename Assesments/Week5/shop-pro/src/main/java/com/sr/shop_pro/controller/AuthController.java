package com.sr.shop_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sr.shop_pro.security.JwtTokenProvider;
import com.sr.shop_pro.domain.Role;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.dto.request.LoginRequest;
import com.sr.shop_pro.dto.response.JwtResponse;
import com.sr.shop_pro.dto.request.UserRequest;
import com.sr.shop_pro.dto.response.UserResponse;
import com.sr.shop_pro.exception.InvalidCredentialsException;
import com.sr.shop_pro.repository.UserRepository;
import com.sr.shop_pro.service.UserService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole().toString());
        
        return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getRole().toString()));
    }

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user account")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        if (userRequest.getRole() == null) {
            userRequest.setRole(Role.CUSTOMER);
        }
        
        UserResponse createdUser = userService.save(userRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}