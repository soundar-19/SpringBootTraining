package com.example.hostel_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostel_management_system.dto.AuthRequestDTO;
import com.example.hostel_management_system.dto.AuthResponseDTO;
import com.example.hostel_management_system.service.JwtService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
        );
        
        String token = jwtService.generateToken(authRequest.getUserName());
        return new AuthResponseDTO(token);
    }
    
}