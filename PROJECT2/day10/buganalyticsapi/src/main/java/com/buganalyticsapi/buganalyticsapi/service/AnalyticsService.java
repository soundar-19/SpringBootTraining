package com.buganalyticsapi.buganalyticsapi.service;

import com.buganalyticsapi.buganalyticsapi.dto.ProjectBugCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.StatusCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.PriorityCountDTO;
import java.util.List;


public interface AnalyticsService {
    List<ProjectBugCountDTO> getBugCountByProject();
    List<StatusCountDTO> getBugCountByStatus();
    List<PriorityCountDTO> getBugCountByPriority();
}