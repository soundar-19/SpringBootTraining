package com.day6proj2.service;

import java.util.List;
import java.util.Scanner;

import com.day6proj2.entity.Bug;
import com.day6proj2.repository.BugRepositoryImpl;

public class BugService {
    private Scanner sc;
    private BugRepositoryImpl bugRepository;
    public BugService(){
        bugRepository = new BugRepositoryImpl();
        sc = new Scanner(System.in);
    }

    public void insertBug(){
        System.out.println("Enter the bug Title");
        String title = sc.nextLine();
        System.out.println("Enter the Bug Description: ");
        String description = sc.nextLine();
        Bug bug = new Bug(title,description,"Open");
        bugRepository.insertBug(bug);
    }

   public void deleteBug(){
    //System.out.println("Enter the id of the bug you want to delete");
    Bug bug = findBugById();
    if(bug != null){
        bugRepository.deleteBugById(bug.getId());
    }
   }
   
   public void updateBugStatusById(){
    Bug bug = findBugById();
    if(bug != null){
        System.out.println("Enter the New Status : ");
        String newStatus = sc.nextLine();
        bugRepository.updateBugStatusById(bug.getId(), newStatus);
    }
    }
    public Bug findBugById(){
        System.out.println("Enter the Bug Id");
        int id = sc.nextInt();
        sc.nextLine();
        return bugRepository.findBugById(id);
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
