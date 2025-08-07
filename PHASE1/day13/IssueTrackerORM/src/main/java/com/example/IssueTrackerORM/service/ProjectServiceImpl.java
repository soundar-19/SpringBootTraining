package com.example.IssueTrackerORM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.repository.BugRepository;
import com.example.IssueTrackerORM.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private BugRepository bugRepository;

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
        if (projectRepository.findByName(project.getName()) != null) {
            throw new IllegalArgumentException("Project with name '" + project.getName() + "' already exists");
        }
        return projectRepository.save(project);
    }
    
    public long countAllProjects() {
        return projectRepository.countAllProjects();
    }
    
    public List<Project> getProjectsWithBugs() {
        return projectRepository.findProjectsWithBugs();
    }
    public Project updateProjectName(String oldName, String newName){
        Project project = projectRepository.findByName(oldName);
        if (project == null) {
            throw new IllegalArgumentException("Project not found with name: " + oldName);
        }
        
        Project existingProject = projectRepository.findByName(newName);
        if (existingProject != null && !existingProject.getId().equals(project.getId())) {
            throw new IllegalArgumentException("Project with name '" + newName + "' already exists");
        }
        
        project.setName(newName);
        return projectRepository.save(project);
    }
    public void deleteProject(Long id){
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            throw new IllegalArgumentException("Project not found with ID: " + id);
        }
        
        long activeBugsCount = bugRepository.countByProjectId(id.intValue());
        if (activeBugsCount > 0) {
            throw new IllegalArgumentException("Cannot delete project with "+activeBugsCount+" active bugs" );
        }
        
        projectRepository.deleteById(id);
    }
}
