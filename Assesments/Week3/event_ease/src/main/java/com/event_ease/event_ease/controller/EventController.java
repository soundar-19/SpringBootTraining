package com.event_ease.event_ease.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event_ease.event_ease.domain.Event;
import com.event_ease.event_ease.dto.EventRequestDTO;
import com.event_ease.event_ease.dto.EventResponseDTO;
import com.event_ease.event_ease.exception.InvalidInputException;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.mapper.EventMapper;
import com.event_ease.event_ease.service.EventService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired 
    private EventMapper eventMapper;

    @GetMapping("/events")
    public  ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> response = new ArrayList<>();
        for(Event event : eventService.getAllEvents()){
            response.add(eventMapper.toDTO(event));
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if(event == null){
            throw new ResourceNotFoundException("Event not found");
        }
        return ResponseEntity.ok(eventMapper.toDTO(event));
    }
    @PostMapping("/events")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody @Valid EventRequestDTO event) {
        Event createdEvent = eventService.createEvent(eventMapper.toEntity(event));
        if(createdEvent == null){
            throw new InvalidInputException("Invalid Input");
        }
        return ResponseEntity.ok(eventMapper.toDTO(createdEvent));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody @Valid EventRequestDTO event) {
        Event updatedEvent = eventService.updateEvent(id, eventMapper.toEntity(event));
        if(updatedEvent == null){
            throw new ResourceNotFoundException("Event not found");
        }
        return ResponseEntity.ok(eventMapper.toDTO(updatedEvent));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

}
