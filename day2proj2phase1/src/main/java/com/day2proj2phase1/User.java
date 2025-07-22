package com.day2proj2phase1;

public class User {
    //properties
    private int userId;
    private String userName;
    private String email;

    //constructor
    User(int userId, String userName, String email){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    //getters
    public int getId() {
        return userId;
    }

    public String getName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
    public void displayDetails(){
        System.out.println("User Details");
        System.out.println("UserId : "+userId);
        System.out.println("UserName : "+userName);
        System.out.println("UserEmail : "+email);
        System.out.println();
    }
}
