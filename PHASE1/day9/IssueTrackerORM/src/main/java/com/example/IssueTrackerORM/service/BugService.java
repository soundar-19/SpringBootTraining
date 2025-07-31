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
}
