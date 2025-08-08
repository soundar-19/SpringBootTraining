package com.student_course_management_system.mapper;

import org.springframework.stereotype.Component;

import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.dto.EnrollmentResponseDTO;

@Component
public class EnrollmentMapper {
    
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    
    public EnrollmentMapper(StudentMapper studentMapper, CourseMapper courseMapper) {
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }
    
    public EnrollmentResponseDTO toDTO(Enrollment enrollment) {
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setStudent(studentMapper.toDTO(enrollment.getStudent()));
        dto.setCourse(courseMapper.toDTO(enrollment.getCourse()));
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        return dto;
    }
}