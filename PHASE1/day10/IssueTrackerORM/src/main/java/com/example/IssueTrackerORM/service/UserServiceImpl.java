package com.example.IssueTrackerORM.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
    
    public long countByRole(String role) {
        return userRepository.countByRole(role);
    }
    
    public List<User> getUsersWithAssignedBugs() {
        return userRepository.findUsersWithAssignedBugs();
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
