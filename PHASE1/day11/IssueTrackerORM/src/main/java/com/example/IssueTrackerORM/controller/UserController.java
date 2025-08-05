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
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable("role") String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/count/role/{role}")
    public long countByRole(@PathVariable("role") String role) {
        return userService.countByRole(role);
    }

    @GetMapping("/with-bugs")
    public List<User> getUsersWithAssignedBugs() {
        return userService.getUsersWithAssignedBugs();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        ResponseEntity<?> responseEntity;
        if(userService.getUserById(id) != null) {
            user.setId(id);
            User updatedUser = userService.createUser(user);
            responseEntity = ResponseEntity.ok(updatedUser);
        } else {
            responseEntity = ResponseEntity.badRequest().body("User not found");
        }
        return responseEntity;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        ResponseEntity<?> responseEntity;
        if(userService.getUserById(id)!=null){
            userService.deleteUser(id);
            responseEntity = ResponseEntity.ok("User With ID "+id+" Deleted Successfully!");
        }else responseEntity = ResponseEntity.badRequest().body("User not found");
        return responseEntity;
    }
}