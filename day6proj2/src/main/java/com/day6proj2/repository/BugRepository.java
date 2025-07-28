package com.day6proj2.repository;

import java.util.List;
import com.day6proj2.entity.Bug;

public interface BugRepository {
    public void insertBug(Bug bug);
    public void deleteBugById(int id);
    public void updateBugStatusById(int id,String status);
    public Bug findBugById(int id);
    public List<Bug> getAllBugs();
} 
