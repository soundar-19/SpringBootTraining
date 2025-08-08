package com.event_ease.event_ease.mapper;

import org.springframework.stereotype.Component;

import com.event_ease.event_ease.domain.Registration;
import com.event_ease.event_ease.dto.RegistrationResponseDTO;

@Component
public class RegistrationMapper {
    public RegistrationResponseDTO toDTO(Registration registration) {
        RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO();
        registrationResponseDTO.setId(registration.getId());
        registrationResponseDTO.setEventId(registration.getEvent().getId());
        registrationResponseDTO.setUserId(registration.getUser().getId());
        registrationResponseDTO.setRegistrationDate(registration.getRegistrationDate());
        return registrationResponseDTO;
    }
}
