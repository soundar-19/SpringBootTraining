package com.event_ease.event_ease.mapper;

import org.springframework.stereotype.Component;

import com.event_ease.event_ease.domain.User;
import com.event_ease.event_ease.dto.UserRequestDTO;
import com.event_ease.event_ease.dto.UserResponseDTO;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        return user;
    }
    public UserResponseDTO toDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
