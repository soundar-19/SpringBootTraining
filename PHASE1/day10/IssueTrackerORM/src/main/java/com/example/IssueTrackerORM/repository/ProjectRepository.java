package com.example.IssueTrackerORM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.IssueTrackerORM.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

} 
    

