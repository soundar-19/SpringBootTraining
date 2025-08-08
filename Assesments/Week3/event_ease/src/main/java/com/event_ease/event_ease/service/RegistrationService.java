package com.event_ease.event_ease.service;

import java.util.List;

import com.event_ease.event_ease.domain.Registration;

public interface RegistrationService {

    Registration register(Long userId, Long eventId);
    void unregister(Long id);
    List<Registration> getAllRegistrations();
    Registration getRegistrationById(Long id);
    Registration updateRegistration(Long id, Long userId, Long eventId);
    List<Registration> getRegistrationsByUserId(Long userId);
    List<Registration> getRegistrationsByEventId(Long eventId);
}
