package com.example.IssueTrackerORM.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.domain.User;
import com.example.IssueTrackerORM.repository.BugRepository;
import com.example.IssueTrackerORM.repository.ProjectRepository;
import com.example.IssueTrackerORM.repository.UserRepository;

@Service
public class BugServiceImpl implements BugService {
    @Autowired
    private BugRepository bugRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
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
            if (project == null) {
                throw new IllegalArgumentException("Project not found with ID: " + projectId);
            }
            if (bugRepository.existsByTitleAndProjectId(bug.getTitle(), projectId)) {
                throw new IllegalArgumentException("Bug with same title already exists in project");
            }
            
            bug.setProject(project);
        }
        if (bug.getStatus() == null || bug.getStatus().trim().isEmpty()) {
            bug.setStatus("Open");
        }
        if (bug.getPriority() == null || bug.getPriority().trim().isEmpty()) {
            bug.setPriority("Medium");
        }
        
        return bugRepository.save(bug);
    }
    
    public Bug getBugById(Long id) {
        return bugRepository.findById(id).orElse(null);
    }
    public Bug UpdateBugStatus(Long id, String status){
        Bug bug = bugRepository.findById(id).orElse(null);
        if (bug == null) {
            throw new IllegalArgumentException("Bug not found with ID: " + id);
        }
        
        bug.setStatus(status);
        return bugRepository.save(bug);
    }
    public Bug assignBugToUser(Long bugId, Long userId) {
        Bug bug = bugRepository.findById(bugId).orElse(null);
        if (bug == null) {
            throw new IllegalArgumentException("Bug not found with ID: " + bugId);
        }
        
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        
        if (!"Developer".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only users with Developer role can be assigned to bugs");
        }
        
        bug.setAssignedTo(user);
        return bugRepository.save(bug);
    }
    
    public void deleteBug(Long id){
        if (!bugRepository.existsById(id)) {
            throw new IllegalArgumentException("Bug not found with ID: " + id);
        }
        bugRepository.deleteById(id);
    }
    
}