package com.sr.shop_pro.dto.response;

import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.domain.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;

    public static UserResponse toDTO(User user){
        UserResponse dto = new UserResponse();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.role = user.getRole();
        return dto;
    }
}