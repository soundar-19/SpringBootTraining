package com.example.IssueTrackerORM.service;

import java.util.List;
import com.example.IssueTrackerORM.domain.Bug;

public interface BugService {
    List<Bug> getAllBugs();
    List<Bug> findByStatus(String status);
    List<Bug> findByPriorityOrderByCreatedDateDesc(String priority);
    List<Bug> findByProjectId(int projectId);
    List<Bug> findByAssignedToId(int userId);
    List<Bug> findUnresolvedBugsByUserId(int userId);
    long countByProjectId(int projectId);
    Bug createBug(Bug bug);
    Bug createBugWithProjectId(Bug bug, Long projectId);
    Bug getBugById(Long id);
    Bug UpdateBugStatus(Long id, String status);
    Bug assignBugToUser(Long bugId, Long userId);
    void deleteBug(Long id);
    
}