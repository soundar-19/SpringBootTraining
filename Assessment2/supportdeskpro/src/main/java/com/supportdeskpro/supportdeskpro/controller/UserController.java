package com.supportdeskpro.supportdeskpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.supportdeskpro.supportdeskpro.domain.User;
import com.supportdeskpro.supportdeskpro.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUsersById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/username/{name}")
    public User getUsersByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
    
}
