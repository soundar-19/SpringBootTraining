package com.example.IssueTrackerORM.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.repository.BugRepository;
import com.example.IssueTrackerORM.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BugRepository bugRepository;
    
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
        if (userRepository.findByName(user.getName()) != null) {
            throw new IllegalArgumentException("User with name '" + user.getName() + "' already exists");
        }
        
        return userRepository.save(user);
    }
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found with ID: " + user.getId());
        }
        
        User userWithSameName = userRepository.findByName(user.getName());
        if (userWithSameName != null && !userWithSameName.getId().equals(user.getId())) {
            throw new IllegalArgumentException("User with name '" + user.getName() + "' already exists");
        }
        existingUser.setName(user.getName());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        
        List<Bug> assignedBugs = bugRepository.findByAssignedToId(id.intValue());
        if (!assignedBugs.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete user with "+assignedBugs.size()+" bugs");
        }
        userRepository.deleteById(id);
    }
    
}
