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
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.service.BugService;


@RestController
@RequestMapping("/api/bugs")
public class BugController {
    @Autowired
    private BugService bugService;

    @GetMapping("/")
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }
    @PostMapping("/create")
    public Bug createBug(@RequestBody Bug bug) {
        Bug newBug = new Bug();
        newBug.setTitle(bug.getTitle());
        newBug.setDescription(bug.getDescription());
        newBug.setStatus(bug.getStatus());
        newBug.setPriority(bug.getPriority());
        
        Long projectId = null;
        if (bug.getProject() != null) {
            projectId = bug.getProject().getId();
        }
        
        return bugService.createBugWithProjectId(newBug, projectId);
    }
    @GetMapping("/status/{status}")
    public List<Bug> findByStatus(@PathVariable("status") String status) {
    return bugService.findByStatus(status);}

    @GetMapping("/project/{projectId}")
    public List<Bug> findByProjectId(@PathVariable("projectId") int projectId){
        return bugService.findByProjectId(projectId);
    }
    @GetMapping("/assignedTo/{userId}")
    public List<Bug> findByAssignedTo(@PathVariable("userId") int userId){
        return bugService.findByAssignedToId(userId);
    }
    @GetMapping("/unresolved/{userId}")
    public List<Bug> findByUnresolved(@PathVariable("userId") int userId){
        return bugService.findUnresolvedBugsByUserId(userId);
    }
    @GetMapping("/count/{projectId}")
    public long countByProjectId(@PathVariable("projectId") int projectId) {
        return bugService.countByProjectId(projectId);
    }
    
    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return bugService.getBugById(id);
    }
    
    @GetMapping("/priority/{priority}")
    public List<Bug> findByPriorityOrderByCreatedDateDesc(@PathVariable("priority") String priority) {
        return bugService.findByPriorityOrderByCreatedDateDesc(priority);
    }
    @PutMapping("/{id}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id,@PathVariable("status") String status ) {
        if (id == null || id <= 0 || status == null || status.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid parameters");
        }
        if (bugService.getBugById(id) != null) {
            return ResponseEntity.ok(bugService.UpdateBugStatus(id, status));
        } else {
            return ResponseEntity.badRequest().body("Bug not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBug(@PathVariable("id") Long id){
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        if (bugService.getBugById(id) != null) {
            bugService.deleteBug(id);
            return ResponseEntity.ok("Bug With ID " + id + " Deleted Successfully!");
        } else {
            return ResponseEntity.badRequest().body("Bug not found");
        }
    }
    
}

