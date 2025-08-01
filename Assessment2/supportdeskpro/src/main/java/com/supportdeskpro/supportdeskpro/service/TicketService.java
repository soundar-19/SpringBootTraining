package com.supportdeskpro.supportdeskpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supportdeskpro.supportdeskpro.domain.Ticket;
import com.supportdeskpro.supportdeskpro.repository.TicketRepository;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    // Constructor injection for TicketRepository
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public void closeTicket(Long id){
        Ticket ticket = getTicketById(id);
        ticket.setStatus("closed");
        ticketRepository.save(ticket);
    }
    public void assignTicketById(Long id, String assignedTo){
        Ticket ticket = getTicketById(id);
        ticket.setAssignedTo(assignedTo);
        ticketRepository.save(ticket);
        System.out.println("Ticket Assigned To : " + assignedTo);
    }
    
}
