package com.supportdeskpro.supportdeskpro.controller;

import org.springframework.web.bind.annotation.RestController;

import com.supportdeskpro.supportdeskpro.domain.Ticket;
import com.supportdeskpro.supportdeskpro.service.TicketService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;







@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    @GetMapping("/tickets/{id}")
    public Ticket getById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
    
    
}

   