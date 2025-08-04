package com.buganalyticsapi.buganalyticsapi.repository;

import com.buganalyticsapi.buganalyticsapi.domain.Bug;
import com.buganalyticsapi.buganalyticsapi.dto.ProjectBugCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.StatusCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.PriorityCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    
    @Query("SELECT new com.buganalyticsapi.buganalyticsapi.dto.ProjectBugCountDTO(p.name, COUNT(b)) FROM Bug b JOIN b.project p GROUP BY p.name")
    List<ProjectBugCountDTO> getBugCountByProject();
    
    @Query("SELECT new com.buganalyticsapi.buganalyticsapi.dto.StatusCountDTO(b.status, COUNT(b)) FROM Bug b GROUP BY b.status")
    List<StatusCountDTO> getBugCountByStatus();
    
    @Query("SELECT new com.buganalyticsapi.buganalyticsapi.dto.PriorityCountDTO(b.priority, COUNT(b)) FROM Bug b GROUP BY b.priority")
    List<PriorityCountDTO> getBugCountByPriority();
}