package com.day1proj1phase1;

public class Ticket {
    //properties
    private int ticketId;
    private String title;
    private String status;

    //constructor
    Ticket(int ticketId, String title){
        this.ticketId = ticketId;
        this.title = title;
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
    public void closeTicket(){
        this.status = "Closed";
    }
    public void displayTicket(){
        System.out.println("Ticket Information");
        System.out.println("Id : "+ticketId);
        System.out.println("Title : "+title);
        System.out.println("Status : "+status);
        System.out.println();
    }
}
