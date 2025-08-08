package com.event_ease.event_ease.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.event_ease.event_ease.domain.User;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.repository.UserRepository;
import com.event_ease.event_ease.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user){
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null){
            user.setId(existingUser.getId());
            return userRepository.save(user);
        }
        return null;
    }
    
    public void deleteUser(Long id){
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser == null){
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.delete(existingUser);
    }
}
