package com.example.hostel_management_system.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO {

    @NotBlank(message = "User Name cannot be empty")
    private String userName;
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public AuthRequestDTO() {}

    public AuthRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}