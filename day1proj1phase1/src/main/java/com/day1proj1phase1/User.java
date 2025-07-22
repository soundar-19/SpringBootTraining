package com.day1proj1phase1;

public class User {
    //properties
    private int id;
    private String name;
    private String email;

    //constructor
    User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public void displayUser(){
        System.out.println("User Details");
        System.out.println("UserId : "+id);
        System.out.println("UserName : "+name);
        System.out.println("UserEmail : "+email);
        System.out.println();
    }
}
