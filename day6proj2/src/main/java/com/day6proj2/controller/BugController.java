package com.day6proj2.controller;

import java.util.Scanner;

import com.day6proj2.service.BugService;

public class BugController {
    private Scanner sc;
    private BugService bugService;
    public BugController(){
        sc = new Scanner(System.in);
        bugService = new BugService();
    }
    public void start(){
        boolean canContinue = true;
        while(canContinue){
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    bugService.insertBug();
                    break;
                case 2:
                    bugService.displayAllBugs();
                    break;
                case 3:
                    bugService.updateBugStatusById();
                    break;
                case 4:
                    bugService.deleteBug();
                    break;
                case 5:
                    canContinue = false;
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, Try again.");
                    break;
            }
        }
    }
    public void showMenu(){
        System.out.println("Select an Option from below : ");
        System.out.println("1. Insert a Bug");
        System.out.println("2. List all the Bugs");
        System.out.println("3. Update Bug Status");
        System.out.println("4. Delete a Bug");
        System.out.println("5. Exit");
    }
}

