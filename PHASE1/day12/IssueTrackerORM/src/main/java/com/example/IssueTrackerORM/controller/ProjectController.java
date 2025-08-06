package com.example.IssueTrackerORM.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> getProjectById(@PathVariable("projectId") Long projectId) {
        if (projectId == null || projectId <= 0) {
            return ResponseEntity.badRequest().body("Invalid project ID");
        }
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project not found");
        }
        return ResponseEntity.ok(project);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProjectByName(@PathVariable("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Project name is required");
        }
        Project project = projectService.getProjectByName(name);
        if (project == null) {
            return ResponseEntity.badRequest().body("Project not found");
        }
        return ResponseEntity.ok(project);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        if (project == null) {
            return ResponseEntity.badRequest().body("Project data is required");
        }
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Project name is required");
        }

        try {
            Project createdProject = projectService.createProject(project);
            return ResponseEntity.ok(createdProject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/count")
    public long countAllProjects() {
        return projectService.countAllProjects();
    }
    
    @GetMapping("/with-bugs")
    public List<Project> getProjectsWithBugs() {
        return projectService.getProjectsWithBugs();
    }
    @PutMapping("/{oldName}/{newName}")
    public ResponseEntity<?> updateProjectName(@PathVariable("oldName") String oldName,@PathVariable("newName") String newName){
        if (oldName == null || oldName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Old project name is required");
        }
        if (newName == null || newName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("New project name is required");
        }
        try {
            Project updatedProject = projectService.updateProjectName(oldName, newName);
            if (updatedProject == null) {
                return ResponseEntity.badRequest().body("Project not found");
            }
            return ResponseEntity.ok(updatedProject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid project ID");
        }
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok("Project With ID "+id+" Deleted Successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
}
