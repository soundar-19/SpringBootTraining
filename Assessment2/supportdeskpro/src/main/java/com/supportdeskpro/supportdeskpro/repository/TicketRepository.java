package com.supportdeskpro.supportdeskpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supportdeskpro.supportdeskpro.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    
} 
