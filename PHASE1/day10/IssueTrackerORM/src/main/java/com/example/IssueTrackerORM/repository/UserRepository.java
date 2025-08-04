package com.example.IssueTrackerORM.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.IssueTrackerORM.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findByRole(String role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    long countByRole(@Param("role") String role);
    
    @Query("SELECT u FROM User u WHERE SIZE(u.bugs) > 0")
    List<User> findUsersWithAssignedBugs();
}
