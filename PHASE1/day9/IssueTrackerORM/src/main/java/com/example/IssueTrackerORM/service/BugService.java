package com.example.IssueTrackerORM.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.repository.BugRepository;

@Service
public class BugService {
    @Autowired
    private BugRepository bugRepository;
    
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }
    public List<Bug> findByStatus(String status){
        return bugRepository.findByStatus(status);
    }
    // public List<Bug> findByPriorityOrderByCreatedDateDesc(String priority){
    //     return bugRepository.findByPriorityOrderByCreatedDateDesc(priority);
    // }
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
}
