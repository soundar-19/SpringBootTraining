package com.example.HelpDeskORM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HelpDeskORM.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
}
