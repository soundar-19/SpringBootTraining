package com.day6proj1phase1.controller;

import java.util.Scanner;

import com.day6proj1phase1.service.BugService;

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
                    System.out.println("Enter the Title of the Bug: ");
                    String title = sc.nextLine();
                    System.out.println("Enter the description: ");
                    String description = sc.nextLine();
                    bugService.insertBug(title, description, "Open");
                    break;
                case 2:
                    bugService.displayAllBugs();
                    break;
                case 3:
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
        System.out.println("3. Exit");
    }
}

