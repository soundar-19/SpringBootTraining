package com.event_ease.event_ease.dto;

import java.time.LocalDate;


public class RegistrationResponseDTO {
    private Long id;
    private Long user_id;
    private Long event_id;
    private LocalDate registrationDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getEvent_id() {
        return event_id;
    }
    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    
}
