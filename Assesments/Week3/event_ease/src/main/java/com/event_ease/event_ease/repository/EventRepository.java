package com.event_ease.event_ease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.event_ease.event_ease.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    
} 
