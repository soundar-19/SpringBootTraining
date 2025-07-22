package com.day1proj1phase2;
import java.util.ArrayList;

public class TicketService {
    private ArrayList<Ticket> ticketList;
    private int ticketId;
    TicketService(){
        ticketList = new ArrayList<>();
        this.ticketId = 1;
    }
    public void addTicket(User user, String ticketTitle){
        Ticket ticket = new Ticket(ticketId++,user,ticketTitle);
        ticketList.add(ticket);
    }
    public void displayTickets(){
        if(ticketList.isEmpty()){
            System.out.println("No tickets to show!");
            return;
        }
        System.out.println("Tickets\n");
        for(Ticket ticket : ticketList){
            ticket.displayTicket();
        }
    }
}
