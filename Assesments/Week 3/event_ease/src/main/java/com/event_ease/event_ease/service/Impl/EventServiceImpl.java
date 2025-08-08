package com.event_ease.event_ease.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event_ease.event_ease.domain.Event;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.repository.EventRepository;
import com.event_ease.event_ease.service.EventService;

@Service
public class EventServiceImpl implements EventService  {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }
    public Event getEventById(Long id){
        return eventRepository.findById(id).orElse(null);
    }
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }   
    public Event updateEvent(Long id, Event event){
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if(existingEvent != null){
            existingEvent.setTitle(event.getTitle());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setDate(event.getDate());
            existingEvent.setLocation(event.getLocation());
            return eventRepository.save(existingEvent);
        }
        return null;
    }
    public void deleteEvent(Long id){
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if(existingEvent == null){
            throw new ResourceNotFoundException("Event not found");
        }
        eventRepository.delete(existingEvent);
    }
}
