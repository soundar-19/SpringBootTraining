package com.day6proj1phase1.repository;

import java.util.List;
import com.day6proj1phase1.entity.Bug;

public interface BugRepository {
    public void insertBug(Bug bug);
    public List<Bug> getAllBugs();
} 
