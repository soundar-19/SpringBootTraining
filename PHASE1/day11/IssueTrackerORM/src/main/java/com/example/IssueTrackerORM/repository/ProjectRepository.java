package com.example.IssueTrackerORM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.IssueTrackerORM.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

    public Project findByName(String name);
    
    @Query("SELECT COUNT(p) FROM Project p")
    long countAllProjects();
    
    @Query("SELECT p FROM Project p WHERE SIZE(p.bugs) > 0")
    List<Project> findProjectsWithBugs();
}