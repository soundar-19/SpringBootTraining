package com.day6proj1phase1.service;

import java.util.List;

import com.day6proj1phase1.entity.Bug;
import com.day6proj1phase1.repository.BugRepository;

public class BugService {
    BugRepository bugRepository;
    public BugService(){
        bugRepository = new BugRepository();
    }
    public void insertBug(String title, String description, String status){
        Bug bug = new Bug(title,description,status);
        bugRepository.insertBug(bug);
    }
    public void displayAllBugs(){
        List<Bug> bugList = bugRepository.getAllBugs();
        if(bugList.isEmpty()){
            System.out.println("There are no Bugs to display!");
        }else{
            System.out.println("Available Bugs: ");
            for(Bug bug : bugList) bug.display();
        }
    }
}
