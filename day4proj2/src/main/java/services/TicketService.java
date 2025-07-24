package services;

import models.Ticket;
import exceptions.InvalidAgeException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import exceptions.EmptyFieldException;

public class TicketService {
    private List<Ticket> tickets;
    public TicketService(){
        tickets = new ArrayList<>();
    }
    public void bookTicket(Ticket ticket) throws InvalidAgeException, EmptyFieldException {
        // Exception handling for ticket booking
        if(ticket.getAge()<18) throw new InvalidAgeException( "Booking failed. User must be at least 18 years old");
        if(ticket.getUserName() == null || ticket.getUserName().isEmpty()) throw new EmptyFieldException("Booking failed. UserName cannot be empty");
        if(ticket.getSource().isEmpty() || ticket.getDestination().isEmpty()) throw new EmptyFieldException("Booking failed. Source and Destination cannot be empty");
        // Check if source and destination are the same
        if(ticket.getSource().equalsIgnoreCase(ticket.getDestination())) {
            throw new InputMismatchException("Booking failed. Source and Destination cannot be the same");
        }
        // Adding ticket to the list
        tickets.add(ticket);

        // Successful booking
        System.out.println("Ticket booked successfully for user: " + ticket.getUserName());
    }
    public void displayAllTickets() {
        if(tickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        // Displaying all tickets
        for(Ticket ticket : tickets) {
            ticket.displayTicket();
        }
    }
}
