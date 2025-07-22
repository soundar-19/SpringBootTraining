package com.day2proj2phase1;

public class Ticket {
    //properties
    private int ticketId;
    private String title;
    private String description;
    private String status;
    private User user;

    //constructor
    Ticket(int ticketId, String title, String description, User user){
        this.ticketId = ticketId;
        this.title = title;
        this.user = user;
        this.description = description;
        this.status = "Open"; //default status
    }
    //getters
    public int getId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
    public String getDescription(){
        return description;
    }
    private User getUser(){
        return user;
    }
    public void closeTicket(){
        this.status = "Closed";
    }
    public void displayDetails(){
        System.out.println("Ticket Information");
        System.out.println("Id : "+ticketId);
        System.out.println("Title : "+title);
        System.out.println("Description : "+description);
        System.out.println("Status : "+status);
        System.out.println();
        user.displayDetails();
    }
}
