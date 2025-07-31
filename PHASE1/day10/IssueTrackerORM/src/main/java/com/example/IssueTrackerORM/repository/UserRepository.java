package com.example.IssueTrackerORM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.IssueTrackerORM.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    
}
