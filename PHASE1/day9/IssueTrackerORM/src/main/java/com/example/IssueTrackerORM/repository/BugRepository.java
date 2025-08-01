package com.example.IssueTrackerORM.repository;
import com.example.IssueTrackerORM.domain.Bug;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BugRepository extends JpaRepository<Bug, Long> {
    List<Bug> findByStatus(String status);

    //List<Bug> findByPriorityOrderByCreatedDateDesc(String priority);
    List<Bug> findByProjectId(int projectId);
    List<Bug> findByAssignedToId(int userId);
    
    @Query("SELECT b FROM Bug b WHERE b.assignedTo.id = :userId AND b.status <> 'Resolved'")
    List<Bug> findUnresolvedBugsByUserId(@Param("userId") int userId);
    
    @Query("SELECT COUNT(b) FROM Bug b WHERE b.project.id = :projectId")
    long countByProjectId(@Param("projectId") int projectId);


}
