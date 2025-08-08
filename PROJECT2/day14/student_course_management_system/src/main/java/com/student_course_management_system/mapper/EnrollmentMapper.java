package com.student_course_management_system.mapper;

import org.springframework.stereotype.Component;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.dto.EnrollmentResponseDTO;

@Component
public class EnrollmentMapper {
    
    public EnrollmentResponseDTO toDTO(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }
        
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getName());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseCode(enrollment.getCourse().getCourseCode());
        dto.setCourseTitle(enrollment.getCourse().getCourseTitle());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus().toString());
        dto.setGrade(enrollment.getGrade());
        dto.setSemester(enrollment.getSemester());
        dto.setYear(enrollment.getYear());
        
        return dto;
    }
}