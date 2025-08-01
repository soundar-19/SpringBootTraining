package com.supportdeskpro.supportdeskpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supportdeskpro.supportdeskpro.domain.User;
import com.supportdeskpro.supportdeskpro.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    // Constructor injection for UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //basic crud functions
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUserName(userDetails.getUserName());
            user.setEmail(userDetails.getEmail());
            //user.setTickets(userDetails.getTickets());
            return userRepository.save(user);
        }
        return null;
    }
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    
}
