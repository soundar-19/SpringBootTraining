package com.example.IssueTrackerORM.service;

import java.util.List;
import com.example.IssueTrackerORM.domain.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String name);
    List<User> getUsersByRole(String role);
    long countByRole(String role);
    List<User> getUsersWithAssignedBugs();
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
