package com.event_ease.event_ease.mapper;

import org.springframework.stereotype.Component;
import com.event_ease.event_ease.domain.Event;
import com.event_ease.event_ease.dto.EventRequestDTO;
import com.event_ease.event_ease.dto.EventResponseDTO;

@Component
public class EventMapper {
    public Event toEntity(EventRequestDTO eventRequestDTO) {
        Event event = new Event();
        event.setTitle(eventRequestDTO.getTitle());
        event.setDescription(eventRequestDTO.getDescription());
        event.setDate(eventRequestDTO.getDate());
        event.setLocation(eventRequestDTO.getLocation());
        return event;
    }
    public EventResponseDTO toDTO(Event event) {
        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setTitle(event.getTitle());
        eventResponseDTO.setDescription(event.getDescription());
        eventResponseDTO.setDate(event.getDate());
        eventResponseDTO.setLocation(event.getLocation());
        return eventResponseDTO;
    }
}
