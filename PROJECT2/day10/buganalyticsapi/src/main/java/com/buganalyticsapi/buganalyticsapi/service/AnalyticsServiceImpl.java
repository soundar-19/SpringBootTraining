package com.buganalyticsapi.buganalyticsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buganalyticsapi.buganalyticsapi.dto.PriorityCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.ProjectBugCountDTO;
import com.buganalyticsapi.buganalyticsapi.dto.StatusCountDTO;
import com.buganalyticsapi.buganalyticsapi.repository.BugRepository;
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    @Autowired
    private BugRepository bugRepository;
    public List<ProjectBugCountDTO> getBugCountByProject(){
        return bugRepository.getBugCountByProject();
    }
    public List<StatusCountDTO> getBugCountByStatus(){
        return bugRepository.getBugCountByStatus();
    }
    public List<PriorityCountDTO> getBugCountByPriority(){
        return bugRepository.getBugCountByPriority();
    }
}
