package com.example.IssueTrackerORM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.service.BugService;

@RestController
@RequestMapping("/api/bugs")
public class BugController {
    @Autowired
    private BugService bugService;

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
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
}

