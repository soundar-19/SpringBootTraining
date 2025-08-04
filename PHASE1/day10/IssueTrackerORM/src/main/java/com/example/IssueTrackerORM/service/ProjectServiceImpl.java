package com.example.IssueTrackerORM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }   
    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    
    public long countAllProjects() {
        return projectRepository.countAllProjects();
    }
    
    public List<Project> getProjectsWithBugs() {
        return projectRepository.findProjectsWithBugs();
    }
}
