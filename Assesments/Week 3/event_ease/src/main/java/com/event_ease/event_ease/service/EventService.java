package com.event_ease.event_ease.service;

import java.util.List;

import com.event_ease.event_ease.domain.Event;

public interface EventService {

    Event createEvent(Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);

} 
