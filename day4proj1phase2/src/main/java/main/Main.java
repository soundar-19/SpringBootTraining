package main;

import services.UserService;
import models.User;
import java.util.Scanner;
import utils.Logger;

public class Main {
    public static void main(String[] args){
        //creation of scanner class object to get inputs from the user
        Scanner sc = new Scanner(System.in);

        //User service creation
        UserService userService = new UserService();

        //Logger initialization
        Logger logger = new Logger("error.log");

        do{
            System.out.println("Welcome, Choose the option you want to perform:");
            System.out.println("1. Register User");
            System.out.println("2. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline left-over
            switch(choice){
                case 1:
                    //User creation
                    System.out.print("Enter username: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    
                    User user = new User(userName, age);
                    
                    //register user
                    try{
                        userService.registerUser(user);
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        logger.log(user.getUserName(),user.getAge(), e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while(true);
        
    }
    
}
