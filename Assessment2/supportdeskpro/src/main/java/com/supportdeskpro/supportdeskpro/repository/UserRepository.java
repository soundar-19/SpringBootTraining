package com.supportdeskpro.supportdeskpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supportdeskpro.supportdeskpro.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    User findByName(String userName);
    

    
} 
