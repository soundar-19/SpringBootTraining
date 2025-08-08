package com.event_ease.event_ease.mapper;

import org.springframework.stereotype.Component;

import com.event_ease.event_ease.domain.Registration;
import com.event_ease.event_ease.dto.RegistrationRequestDTO;
import com.event_ease.event_ease.dto.RegistrationResponseDTO;

@Component
public class RegistrationMapper {
    public Registration toEntity(RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = new Registration();
        registration.setEvent_id(registrationRequestDTO.getEventId());
        registration.setUser_id(registrationRequestDTO.getUserId());
        return registration;
    }
    public RegistrationResponseDTO toDTO(Registration registration) {
        RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO();
        registrationResponseDTO.setId(registration.getId());
        registrationResponseDTO.setEvent_id(registration.getEvent_id());
        registrationResponseDTO.setUser_id(registration.getUser_id());
        registrationResponseDTO.setRegistrationDate(registration.getRegistration_date());
        return registrationResponseDTO;
    }
}
