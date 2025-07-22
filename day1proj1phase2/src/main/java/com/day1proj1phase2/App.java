package com.day1proj1phase2;

public class App 
{
    public static void main( String[] args )
    {
        //user Creation
        User user1 = new User(1, "Soundar Raja B", "soundarrajacf@gmail.com");
        User user2 = new User(2, "Soundar", "soundar@gmail.com");
        User user3 = new User(3, "Raja", "raja@gmail.com");

        //Initializing Ticket Service
        TicketService ticketService = new TicketService();

        //Tickets Creation
        ticketService.addTicket(user1, "Technical Issue");
        ticketService.addTicket(user2, "Login Issue");
        ticketService.addTicket(user3, "Hardware Issue");
        ticketService.displayTickets();        
        
    }
}
