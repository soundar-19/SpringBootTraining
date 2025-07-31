package com.example.IssueTrackerORM.repository;
import com.example.IssueTrackerORM.domain.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
    
}
