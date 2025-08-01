package com.supportdesk_console.repository;

import java.util.List;
import java.util.Optional;

import com.supportdeskpro.supportdeskpro.domain.Ticket;

public interface TicketRepository {
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);
    Ticket save(Ticket ticket);
}
