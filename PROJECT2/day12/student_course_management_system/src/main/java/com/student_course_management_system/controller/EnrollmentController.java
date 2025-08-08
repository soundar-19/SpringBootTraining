package com.student_course_management_system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.dto.EnrollmentResponseDTO;
import com.student_course_management_system.exception.ResourceNotFoundException;
import com.student_course_management_system.mapper.EnrollmentMapper;
import com.student_course_management_system.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    
    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper enrollmentMapper;
    
    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentMapper enrollmentMapper) {
        this.enrollmentService = enrollmentService;
        this.enrollmentMapper = enrollmentMapper;
    }
    
    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        List<EnrollmentResponseDTO> response = new ArrayList<>();
        for(Enrollment enrollment : enrollmentService.getAllEnrollments()) {
            response.add(enrollmentMapper.toDTO(enrollment));
        }
        if(response.isEmpty()) throw new ResourceNotFoundException("No enrollments found");
        return ResponseEntity.ok(response);
    }
}