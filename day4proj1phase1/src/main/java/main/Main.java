package main;

import services.UserService;
import models.User;

public class Main {
    public static void main(String[] args){
        //User service creation
        UserService userService = new UserService();
        
        //User creation
        User user1 = new User("Sachin", 10);
        User user2 = new User("Virat",18);
        User user3 = new User("Dhoni",7);
        User user4 = new User("Ashwin",99);

        User[] users = {user1,user2,user3,user4};
        
        //register users
        for(User user:users){
            try{
                userService.registerUser(user);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    
}
