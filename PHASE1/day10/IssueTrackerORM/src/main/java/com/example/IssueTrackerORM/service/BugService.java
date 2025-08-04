package com.example.IssueTrackerORM.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.repository.BugRepository;
import com.example.IssueTrackerORM.repository.ProjectRepository;

@Service
public class BugService {
    @Autowired
    private BugRepository bugRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }
    public List<Bug> findByStatus(String status){
        return bugRepository.findByStatus(status);
    }
    public List<Bug> findByPriorityOrderByCreatedDateDesc(String priority){
        return bugRepository.findByPriorityOrderByCreatedDateDesc(priority);
    }
    public List<Bug> findByProjectId(int projectId){
        return bugRepository.findByProjectId(projectId);
    }
    public List<Bug> findByAssignedToId(int userId){
        return bugRepository.findByAssignedToId(userId);
    }
    public List<Bug> findUnresolvedBugsByUserId(int userId) {
        return bugRepository.findUnresolvedBugsByUserId(userId);
    }
    public long countByProjectId(int projectId) {
        return bugRepository.countByProjectId(projectId);
    }
    public Bug createBug(Bug bug) {
        return bugRepository.save(bug);
    }
    
    public Bug createBugWithProjectId(Bug bug, Long projectId) {
        if (projectId != null) {
            Project project = projectRepository.findById(projectId).orElse(null);
            bug.setProject(project);
        }
        return bugRepository.save(bug);
    }
    
    public Bug getBugById(Long id) {
        return bugRepository.findById(id).orElse(null);
    }
}
