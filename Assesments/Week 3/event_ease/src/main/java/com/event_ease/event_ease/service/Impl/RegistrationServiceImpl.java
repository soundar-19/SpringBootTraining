package com.event_ease.event_ease.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event_ease.event_ease.domain.Registration;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.repository.RegistrationRepository;
import com.event_ease.event_ease.service.EventService;
import com.event_ease.event_ease.service.RegistrationService;
import com.event_ease.event_ease.service.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private RegistrationRepository registrationRepository;
    private UserService userService;
    private EventService eventService;

    public RegistrationServiceImpl(){}

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository,UserServiceImpl userServiceImpl, EventServiceImpl eventServiceImpl){
        this.registrationRepository = registrationRepository;
        this.userService = userServiceImpl;
        this.eventService = eventServiceImpl;
    }

    public Registration register(Long userId, Long eventId){

        if(userService.getUserById(userId) == null){
            throw new ResourceNotFoundException("User not found");
        }
        if(eventService.getEventById(eventId) == null){
            throw new ResourceNotFoundException("Event not found");
        }
        Registration registration = new Registration();
        registration.setUser_id(userId);
        registration.setEvent_id(eventId);
        registration.setRegistration_date(java.time.LocalDate.now());
        return registrationRepository.save(registration);
    }

    public void unregister(Long id){
        if(!registrationRepository.existsById(id)){
            throw new ResourceNotFoundException("Registration not found");
        }
        registrationRepository.deleteById(id);
    }

    public List<Registration> getAllRegistrations(){
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id){
        return registrationRepository.findById(id).orElse(null);
    }
    
    public Registration updateRegistration(Long id, Long userId, Long eventId){
        if(userService.getUserById(userId) == null){
            throw new ResourceNotFoundException("User not found");
        }
        if(eventService.getEventById(eventId) == null){
            throw new ResourceNotFoundException("Event not found");
        }
        Registration existingRegistration = registrationRepository.findById(id).orElse(null);
        if(existingRegistration != null){
            existingRegistration.setUser_id(userId);
            existingRegistration.setEvent_id(eventId);
            return registrationRepository.save(existingRegistration);
        }
        return null;
    }

    public List<Registration> getRegistrationsByUserId(Long userId) {
        if(userService.getUserById(userId) == null){
            throw new ResourceNotFoundException("User not found");
        }
        return registrationRepository.findAllByUserId(userId);
    }

    public List<Registration> getRegistrationsByEventId(Long eventId) {
        if(eventService.getEventById(eventId) == null){
            throw new ResourceNotFoundException("Event not found");
        }
        return registrationRepository.findAllByEventId(eventId);
    }
    
}
