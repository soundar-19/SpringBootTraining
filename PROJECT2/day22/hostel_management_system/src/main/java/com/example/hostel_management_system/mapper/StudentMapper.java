package com.example.hostel_management_system.mapper;

import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.dto.StudentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    

    public StudentResponseDto toResponseDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAddress(student.getAddress());
        return dto;
    }
    

}