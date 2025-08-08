package com.event_ease.event_ease.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "registrations")
public class Registration {
    //properties
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private Long event_id;
    private LocalDate registration_date;

    //getters and setters

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
    public LocalDate getRegistration_date() {
        return registration_date;
    }
    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

}
