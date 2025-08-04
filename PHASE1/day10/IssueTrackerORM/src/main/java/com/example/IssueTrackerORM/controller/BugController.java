package com.example.IssueTrackerORM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Bug createBug(@RequestBody Map<String, Object> request) {
        Bug bug = new Bug();
        bug.setTitle((String) request.get("title"));
        bug.setDescription((String) request.get("description"));
        bug.setStatus((String) request.get("status"));
        bug.setPriority((String) request.get("priority"));
        
        Long projectId = null;
        if (request.get("project") instanceof Number) {
            projectId = ((Number) request.get("project")).longValue();
        }
        
        return bugService.createBugWithProjectId(bug, projectId);
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
        return bugService.getBugById(id);
    }
    
    @GetMapping("/priority/{priority}")
    public List<Bug> findByPriorityOrderByCreatedDateDesc(@PathVariable("priority") String priority) {
        return bugService.findByPriorityOrderByCreatedDateDesc(priority);
    }
}

