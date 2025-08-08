package com.event_ease.event_ease.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.event_ease.event_ease.domain.Registration;
import com.event_ease.event_ease.dto.RegistrationRequestDTO;
import com.event_ease.event_ease.dto.RegistrationResponseDTO;
import com.event_ease.event_ease.exception.ResourceNotFoundException;
import com.event_ease.event_ease.mapper.RegistrationMapper;
import com.event_ease.event_ease.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationMapper registrationMapper;

    @GetMapping("/registrations")
    public ResponseEntity<List<RegistrationResponseDTO>> getAllRegistrations() {
        List<RegistrationResponseDTO> response = registrationService.getAllRegistrations()
                .stream()
                .map(registrationMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/registrations/{id}")
    public ResponseEntity<RegistrationResponseDTO> getRegistrationById(@PathVariable Long id) {
        Registration registration = registrationService.getRegistrationById(id);
        if (registration == null) {
            throw new ResourceNotFoundException("Registration not found");
        }
        return ResponseEntity.ok(registrationMapper.toDTO(registration));
    }

    @PostMapping("/registrations")
    public ResponseEntity<RegistrationResponseDTO> createRegistration(@RequestBody @Valid RegistrationRequestDTO request) {
        Registration registration = registrationService.register(request.getUserId(), request.getEventId());
        return ResponseEntity.ok(registrationMapper.toDTO(registration));
    }

    @PutMapping("/registrations/{id}")
    public ResponseEntity<RegistrationResponseDTO> updateRegistration(@PathVariable Long id, @RequestBody @Valid RegistrationRequestDTO request) {
        Registration registration = registrationService.updateRegistration(id, request.getUserId(), request.getEventId());
        if (registration == null) {
            throw new ResourceNotFoundException("Registration not found");
        }
        return ResponseEntity.ok(registrationMapper.toDTO(registration));
    }

    @DeleteMapping("/registrations/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable Long id) {
        registrationService.unregister(id);
        return ResponseEntity.ok("Registration deleted successfully");
    }

    @GetMapping("/users/{userId}/registrations")
    public ResponseEntity<List<RegistrationResponseDTO>> getRegistrationsByUserId(@PathVariable Long userId) {
        List<RegistrationResponseDTO> response = registrationService.getRegistrationsByUserId(userId)
                .stream()
                .map(registrationMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/events/{eventId}/registrations")
    public ResponseEntity<List<RegistrationResponseDTO>> getRegistrationsByEventId(@PathVariable Long eventId) {
        List<RegistrationResponseDTO> response = registrationService.getRegistrationsByEventId(eventId)
                .stream()
                .map(registrationMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}