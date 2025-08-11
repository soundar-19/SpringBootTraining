package com.ex.bug_tracker_security_basic_auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.bug_tracker_security_basic_auth.dto.BugResponseDTO;
import com.ex.bug_tracker_security_basic_auth.repository.BugRepository;

@Service
public class BugService {
    private final BugRepository bugRepository;
    
    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    //LINQ for filter
    public List<BugResponseDTO> filterBugs(String status, String assignee, String project){
        return bugRepository.findAll().stream()
        .filter(bug -> status == null || bug.getStatus().equalsIgnoreCase(status))
        .filter(bug -> assignee == null || bug.getAssignee().equalsIgnoreCase(assignee))
        .filter(bug -> project == null || bug.getProject().equalsIgnoreCase(project))
        .map(bug -> new BugResponseDTO(bug.getId(), bug.getTitle(), bug.getStatus(), bug.getAssignee(), bug.getProject()))
        .toList();
    }

    public List<BugResponseDTO> getByStatus(String status) {
        return bugRepository.findAll().stream()
        .filter(bug-> bug.getStatus().equalsIgnoreCase(status))
        .map(bug -> new BugResponseDTO(bug.getId(),bug.getTitle(),bug.getStatus(),bug.getAssignee(),bug.getProject())).toList();
    }

    public BugResponseDTO getBugById(Long id){
        return bugRepository.findById(id)
        .map(bug -> new BugResponseDTO(bug.getId(), bug.getTitle(), bug.getStatus(), bug.getAssignee(), bug.getProject()))
        .orElse(null);
    }   

    public List<BugResponseDTO> getByAssignee(String assignee) {
        return bugRepository.findAll().stream()
        .filter(bug -> bug.getAssignee().equalsIgnoreCase(assignee))
        .map(bug -> new BugResponseDTO(bug.getId(), bug.getTitle(), bug.getStatus(), bug.getAssignee(), bug.getProject())).toList();
    }

    public List<BugResponseDTO> getByProject(String project) {
        return bugRepository.findAll().stream()
        .filter(bug -> bug.getProject().equalsIgnoreCase(project))
        .map(bug -> new BugResponseDTO(bug.getId(), bug.getTitle(), bug.getStatus(), bug.getAssignee(), bug.getProject())).toList();
    }
}
