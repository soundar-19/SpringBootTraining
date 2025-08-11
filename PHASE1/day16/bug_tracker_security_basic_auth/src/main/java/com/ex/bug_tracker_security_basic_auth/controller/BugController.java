package com.ex.bug_tracker_security_basic_auth.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ex.bug_tracker_security_basic_auth.dto.BugResponseDTO;
import com.ex.bug_tracker_security_basic_auth.service.BugService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/bugs")    
public class BugController {
    private final BugService bugService;

    public BugController(BugService bugService){
        this.bugService = bugService;
    }
    @GetMapping
    public ResponseEntity<List<BugResponseDTO>> getAllByFilter(@RequestParam(required = false) String status, @RequestParam(required = false) String assignee, @RequestParam(required = false) String project){
        return ResponseEntity.ok(bugService.filterBugs(status, assignee, project));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<BugResponseDTO> getBugById(@PathVariable Long id){
        return ResponseEntity.ok(bugService.getBugById(id));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BugResponseDTO>> getBugByStatus(@PathVariable String status){
        return ResponseEntity.ok(bugService.getByStatus(status));
    }
    
    @GetMapping("/assignee/{assignee}")
    public ResponseEntity<List<BugResponseDTO>> getBugsByAssignee(@PathVariable String assignee){
        return ResponseEntity.ok(bugService.getByAssignee(assignee));
    }
    
    @GetMapping("/project/{project}")
    public ResponseEntity<List<BugResponseDTO>> getBugsByProject(@PathVariable String project){
        return ResponseEntity.ok(bugService.getByProject(project));
    }
}
