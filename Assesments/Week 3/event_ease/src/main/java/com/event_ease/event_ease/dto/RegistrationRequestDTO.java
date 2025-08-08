package com.event_ease.event_ease.dto;

import jakarta.validation.constraints.NotNull;

public class RegistrationRequestDTO {
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Event ID is required")
    private Long eventId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    
}
