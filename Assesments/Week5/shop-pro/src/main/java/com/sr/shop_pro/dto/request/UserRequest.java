package com.sr.shop_pro.dto.request;

import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.domain.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull(message = "User role is required")
    private Role role;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    public User toEntity(){
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setPassword(this.password);
        return user;
    }
}