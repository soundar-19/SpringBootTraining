package com.example.IssueTrackerORM.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    } 
    @GetMapping("/id/{projectId}")
    public Project getProjectById(@PathVariable("projectId") Long projectId) {
        return projectService.getProjectById(projectId);
    }
    @GetMapping("/name/{name}")
    public Project getProjectByName(@PathVariable("name") String name) {
        return projectService.getProjectByName(name);
    }
    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }
    
    @GetMapping("/count")
    public long countAllProjects() {
        return projectService.countAllProjects();
    }
    
    @GetMapping("/with-bugs")
    public List<Project> getProjectsWithBugs() {
        return projectService.getProjectsWithBugs();
    }
}
