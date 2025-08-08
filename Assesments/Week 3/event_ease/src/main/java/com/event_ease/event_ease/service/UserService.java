package com.event_ease.event_ease.service;

import java.util.List;

import com.event_ease.event_ease.domain.User;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    
} 