package com.example.IssueTrackerORM.controller;

import java.util.ArrayList;
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
import com.example.IssueTrackerORM.dto.ProjectRequestDTO;
import com.example.IssueTrackerORM.dto.ProjectResponseDTO;
import com.example.IssueTrackerORM.exception.InvalidInputException;
import com.example.IssueTrackerORM.exception.ResourceNotFoundException;
import com.example.IssueTrackerORM.mapper.ProjectMapper;
import com.example.IssueTrackerORM.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<ProjectResponseDTO> getAllProjects() {
        List<ProjectResponseDTO> response = new ArrayList<>();
        for(Project project : projectService.getAllProjects()){
            response.add(ProjectMapper.toDTO(project));
        }
        if(response.isEmpty()){
            throw new ResourceNotFoundException("No projects found");
        }
        return response;
    } 

    @GetMapping("/id/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable("projectId") Long projectId) {
        if (projectId == null || projectId <= 0) throw new InvalidInputException("Invalid input");
        Project project = projectService.getProjectById(projectId);
        if (project == null) throw new ResourceNotFoundException("Project Not Found");
        return ResponseEntity.ok(ProjectMapper.toDTO(project));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ProjectResponseDTO> getProjectByName(@PathVariable("name") String name) {
        if(name == null || name.trim().isEmpty()) throw new InvalidInputException("Name Cannot be Empty");
        Project project = projectService.getProjectByName(name);
        if (project == null) throw new ResourceNotFoundException("Project Not Found");
        return ResponseEntity.ok(ProjectMapper.toDTO(project));
    }
    @PostMapping("/create")
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO project) {
        if (project == null) {
            throw new InvalidInputException("Project Data is Missing");
        }
        Project createdProject = projectService.createProject(ProjectMapper.toEntity(project));
        return ResponseEntity.ok(ProjectMapper.toDTO(createdProject));
    }
    
    @GetMapping("/count")
    public long countAllProjects() {
        return projectService.countAllProjects();
    }
    
    @GetMapping("/with-bugs")
    public List<ProjectResponseDTO> getProjectsWithBugs() {
        List<ProjectResponseDTO> response = new ArrayList<>();
        for(Project project : projectService.getProjectsWithBugs()){
            response.add(ProjectMapper.toDTO(project));
        }
        return response;
    }

    @PutMapping("/{oldName}/{newName}")
    public ResponseEntity<ProjectResponseDTO> updateProjectName(@PathVariable("oldName") String oldName,@PathVariable("newName") String newName){
        if (oldName == null || oldName.trim().isEmpty()) {
            throw new InvalidInputException("Old project name is required");
        }
        if (newName == null || newName.trim().isEmpty()) {
            throw new InvalidInputException("New project name is required");
        }
            Project updatedProject = projectService.updateProjectName(oldName, newName);
            if (updatedProject == null) {
                throw new ResourceNotFoundException("Project not found with name: " + oldName);
            }
            return ResponseEntity.ok(ProjectMapper.toDTO(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid project ID: " + id);
        }
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok(String.format("Project with ID %d deleted successfully!", id));
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }   
}
