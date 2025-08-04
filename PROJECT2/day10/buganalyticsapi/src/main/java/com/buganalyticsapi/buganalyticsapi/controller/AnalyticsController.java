package com.buganalyticsapi.buganalyticsapi.controller;

import com.buganalyticsapi.buganalyticsapi.dto.ProjectBugCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.StatusCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.PriorityCountDTO;
import com.buganalyticsapi.buganalyticsapi.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/bugs-by-project")
    public List<ProjectBugCountDTO> getBugCountByProject() {
        return analyticsService.getBugCountByProject();
    }
    
    @GetMapping("/bugs-by-status")
    public List<StatusCountDTO> getBugCountByStatus() {
        return analyticsService.getBugCountByStatus();
    }
    
    @GetMapping("/bugs-by-priority")
    public List<PriorityCountDTO> getBugCountByPriority() {
        return analyticsService.getBugCountByPriority();
    }
}