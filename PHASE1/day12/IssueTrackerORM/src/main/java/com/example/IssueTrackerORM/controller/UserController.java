package com.example.IssueTrackerORM.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User name is required");
        }
        User user = userService.getUserByName(name);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable("role") String role) {
        if (role == null || role.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Role is required");
        }
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/count/role/{role}")
    public ResponseEntity<?> countByRole(@PathVariable("role") String role) {
        if (role == null || role.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Role is required");
        }
        return ResponseEntity.ok(userService.countByRole(role));
    }

    @GetMapping("/with-bugs")
    public List<User> getUsersWithAssignedBugs() {
        return userService.getUsersWithAssignedBugs();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("User data is required");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User name is required");
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User role is required");
        }
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }
        if (user == null) {
            return ResponseEntity.badRequest().body("User data is required");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User name is required");
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User role is required");
        }
        try {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            if (updatedUser == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User With ID "+id+" Deleted Successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}