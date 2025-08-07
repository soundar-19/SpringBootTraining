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
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.dto.BugRequestDTO;
import com.example.IssueTrackerORM.dto.BugResponseDTO;
import com.example.IssueTrackerORM.exception.InvalidInputException;
import com.example.IssueTrackerORM.exception.ResourceNotFoundException;
import com.example.IssueTrackerORM.mapper.BugMapper;
import com.example.IssueTrackerORM.service.BugService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/bugs")
public class BugController {
    @Autowired
    private BugService bugService;

    @GetMapping("/")
    public ResponseEntity<List<BugResponseDTO>> getAllBugs() {
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.getAllBugs()){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<BugResponseDTO> createBug(@Valid @RequestBody BugRequestDTO bugRequestDTO) {
        if (bugRequestDTO == null) {
            throw new InvalidInputException("Bug data is required");
        }
        if (bugRequestDTO.getTitle() == null || bugRequestDTO.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Title is required");
        }
        Bug newBug = BugMapper.toEntity(bugRequestDTO);
        Long projectId = bugRequestDTO.getProjectId();
        Bug createdBug = bugService.createBugWithProjectId(newBug, projectId);
        return ResponseEntity.ok(BugMapper.toDTO(createdBug));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BugResponseDTO>> findByStatus(@PathVariable("status") String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException("Status is required");
        }
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.findByStatus(status)){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<BugResponseDTO>> findByProjectId(@PathVariable("projectId") int projectId){
        if (projectId <= 0) {
            throw new InvalidInputException("Invalid project ID: " + projectId);
        }
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.findByProjectId(projectId)){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/assignedTo/{userId}")
    public ResponseEntity<List<BugResponseDTO>> findByAssignedTo(@PathVariable("userId") int userId){
        if (userId <= 0) {
            throw new InvalidInputException("Invalid user ID: " + userId);
        }
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.findByAssignedToId(userId)){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/unresolved/{userId}")
    public ResponseEntity<List<BugResponseDTO>> findByUnresolved(@PathVariable("userId") int userId){
        if (userId <= 0) {
            throw new InvalidInputException("Invalid user ID: " + userId);
        }
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.findUnresolvedBugsByUserId(userId)){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/count/{projectId}")
    public ResponseEntity<Long> countByProjectId(@PathVariable("projectId") int projectId) {
        if (projectId <= 0) {
            throw new InvalidInputException("Invalid project ID: " + projectId);
        }
        return ResponseEntity.ok(bugService.countByProjectId(projectId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BugResponseDTO> getBugById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid bug ID: " + id);
        }
        Bug bug = bugService.getBugById(id);
        if (bug == null) {
            throw new ResourceNotFoundException("Bug not found with ID: " + id);
        }
        return ResponseEntity.ok(BugMapper.toDTO(bug));
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<BugResponseDTO>> findByPriorityOrderByCreatedDateDesc(@PathVariable("priority") String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            throw new InvalidInputException("Priority is required");
        }
        List<BugResponseDTO> response = new ArrayList<>();
        for(Bug bug : bugService.findByPriorityOrderByCreatedDateDesc(priority)){
            response.add(BugMapper.toDTO(bug));
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/{status}")
    public ResponseEntity<BugResponseDTO> updateStatus(@PathVariable("id") Long id,@PathVariable("status") String status ) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid bug ID: " + id);
        }
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException("Status is required");
        }
        try {
            Bug updatedBug = bugService.UpdateBugStatus(id, status);
            if (updatedBug == null) {
                throw new ResourceNotFoundException("Bug not found with ID: " + id);
            }
            return ResponseEntity.ok(BugMapper.toDTO(updatedBug));
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBug(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid bug ID: " + id);
        }
        try {
            Bug bug = bugService.getBugById(id);
            if (bug == null) {
                throw new ResourceNotFoundException("Bug not found with ID: " + id);
            }
            bugService.deleteBug(id);
            return ResponseEntity.ok(String.format("Bug with ID %d deleted successfully!", id));
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    
    
}

