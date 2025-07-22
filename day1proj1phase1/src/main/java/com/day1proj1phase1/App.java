package com.day1proj1phase1;

public class App 
{
    public static void main( String[] args )
    {
        //user Creation
        User user1 = new User(143, "Soundar Raja B", "soundarrajacf@gmail.com");
        user1.displayUser();

        //ticket Creation
        Ticket ticket1 = new Ticket(1, "Technical Issue");
        ticket1.displayTicket();
        
        //closing the ticket
        ticket1.closeTicket();
        ticket1.displayTicket();
    }
}
