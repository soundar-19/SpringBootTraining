package com.student_course_management_system.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.dto.EnrollmentRequestDTO;
import com.student_course_management_system.dto.EnrollmentResponseDTO;
import com.student_course_management_system.mapper.EnrollmentMapper;
import com.student_course_management_system.service.EnrollmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/enrollments")
@Validated
public class EnrollmentController {
    
    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper enrollmentMapper;
    
    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentMapper enrollmentMapper) {
        this.enrollmentService = enrollmentService;
        this.enrollmentMapper = enrollmentMapper;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequest) {
        Enrollment enrollment = enrollmentService.enrollStudent(enrollmentRequest);
        EnrollmentResponseDTO responseDTO = enrollmentMapper.toDTO(enrollment);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<String> unenrollStudent(
            @PathVariable @Positive(message = "Student ID must be positive") Long studentId,
            @PathVariable @Positive(message = "Course ID must be positive") Long courseId) {
        
        boolean success = enrollmentService.unenrollStudent(studentId, courseId);
        if (success) {
            return ResponseEntity.ok("Student unenrolled successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getStudentEnrollments(
            @PathVariable @Positive(message = "Student ID must be positive") Long studentId) {
        
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
        List<EnrollmentResponseDTO> responseDTOs = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            responseDTOs.add(enrollmentMapper.toDTO(enrollment));
        }
        
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getCourseEnrollments(
            @PathVariable @Positive(message = "Course ID must be positive") Long courseId) {
        
        List<Enrollment> enrollments = enrollmentService.getCourseEnrollments(courseId);
        List<EnrollmentResponseDTO> responseDTOs = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            responseDTOs.add(enrollmentMapper.toDTO(enrollment));
        }
        
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        List<EnrollmentResponseDTO> responseDTOs = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            responseDTOs.add(enrollmentMapper.toDTO(enrollment));
        }
        
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{enrollmentId}/grade")
    public ResponseEntity<EnrollmentResponseDTO> updateGrade(
            @PathVariable @Positive(message = "Enrollment ID must be positive") Long enrollmentId,
            @RequestParam String grade) {
        
        Enrollment enrollment = enrollmentService.updateGrade(enrollmentId, grade);
        EnrollmentResponseDTO responseDTO = enrollmentMapper.toDTO(enrollment);
        return ResponseEntity.ok(responseDTO);
    }
}