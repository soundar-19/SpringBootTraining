package com.example.IssueTrackerORM.service;

import java.util.List;

import com.example.IssueTrackerORM.domain.Project;

public interface ProjectService {

    List<Project> getAllProjects();
    Project getProjectById(Long projectId);
    Project getProjectByName(String name);
    Project createProject(Project project);
    long countAllProjects();
    List<Project> getProjectsWithBugs();
    Project updateProjectName(String oldName,String newName);
    void deleteProject(Long id);
} 