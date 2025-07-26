package com.day5proj2.main;
import java.util.*;

import com.day5proj2.dao.BugDAO;
import com.day5proj2.models.Bug;
public class Main {
    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in)){
            BugDAO bugDAO = new BugDAO();
            boolean canContinue = true;
            while(canContinue){
                showMenu();
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the Title of the Bug");
                        String title = sc.nextLine();
                        System.out.println("Enter the description");
                        String description = sc.nextLine();
                        Bug bug = new Bug(title,description,"Open");
                        bugDAO.insertBug(bug);
                        break;
                    case 2:
                        List<Bug> bugList = bugDAO.getAllBugs();
                        for(Bug bg : bugList) bg.display();
                        break;
                    case 3:
                        System.out.println("Enter the ID of the Bug");
                        String id = sc.nextLine();
                        bugDAO.deleteBugById(id);
                        break;
                    case 4:
                        System.out.println("Enter the ID of the Bug");
                        String updateId = sc.nextLine();
                        System.out.println("Enter the new Status");
                        String status = sc.nextLine();
                        bugDAO.updateBugStatusById(updateId, status);
                        break;
                    case 5:
                        System.out.println("Exiting....");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public static void showMenu(){
        System.out.println("\nWelcome to Bug Tracker\nSelect an option from below:");
        System.out.println("1. Insert a new Bug\n2. View All Bugs\n3. Delete a Bug by ID\n4. Update Bug status by ID\n5. Exit");
    }
}
