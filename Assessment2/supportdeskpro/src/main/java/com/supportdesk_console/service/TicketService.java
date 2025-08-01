package com.supportdesk_console.service;

import java.util.List;

import com.supportdesk_console.exception.TicketNotFoundException;
import com.supportdesk_console.repository.TicketRepositoryImpl;
import com.supportdeskpro.supportdeskpro.domain.Ticket;

public class TicketService {
    private TicketRepositoryImpl ticketRepository;

    public TicketService() {
        this.ticketRepository = new TicketRepositoryImpl();
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void closeTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus("closed");
        ticketRepository.save(ticket);
    }

    public void assignTicketById(Long id, String assignedTo) {
        Ticket ticket = getTicketById(id);
        ticket.setAssignedTo(assignedTo);
        ticketRepository.save(ticket);
        System.out.println("Ticket Assigned To : " + assignedTo);
    }
}
