package com.supportdesk_console.service;

import java.util.List;

import com.supportdesk_console.exception.UserNotFoundException;
import com.supportdesk_console.repository.UserRepositoryImpl;
import com.supportdeskpro.supportdeskpro.domain.User;

public class UserService {
    private UserRepositoryImpl userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryImpl();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }
}
