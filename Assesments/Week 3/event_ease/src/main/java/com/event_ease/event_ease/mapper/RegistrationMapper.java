package com.event_ease.event_ease.mapper;

import org.springframework.stereotype.Component;

import com.event_ease.event_ease.domain.Registration;
import com.event_ease.event_ease.dto.RegistrationRequestDTO;
import com.event_ease.event_ease.dto.RegistrationResponseDTO;

@Component
public class RegistrationMapper {
    public Registration toEntity(RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = new Registration();
        return registration;
    }
    public RegistrationResponseDTO toDTO(Registration registration) {
        RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO();
        registrationResponseDTO.setId(registration.getId());
        registrationResponseDTO.setEvent_id(registration.getEvent().getId());
        registrationResponseDTO.setUser_id(registration.getUser().getId());
        registrationResponseDTO.setRegistrationDate(registration.getRegistrationDate());
        return registrationResponseDTO;
    }
}
