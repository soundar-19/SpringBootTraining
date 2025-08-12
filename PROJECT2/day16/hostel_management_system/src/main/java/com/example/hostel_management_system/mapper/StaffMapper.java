package com.example.hostel_management_system.mapper;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.dto.StaffResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {
    

    public StaffResponseDto toResponseDto(Staff staff) {
        StaffResponseDto dto = new StaffResponseDto();
        dto.setId(staff.getId());
        dto.setName(staff.getName());
        dto.setEmail(staff.getEmail());
        dto.setAddress(staff.getAddress());
        dto.setRole(staff.getRole());
        dto.setStudentCount(staff.getStudents() != null ? staff.getStudents().size() : 0);
        return dto;
    }
    

}