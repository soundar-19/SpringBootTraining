package com.example.IssueTrackerORM.mapper;

import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.dto.UserRequestDTO;
import com.example.IssueTrackerORM.dto.UserResponseDTO;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        return user;
    }
    public static UserResponseDTO toDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }
}
