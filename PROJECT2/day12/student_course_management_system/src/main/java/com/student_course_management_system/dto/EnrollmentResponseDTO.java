package com.student_course_management_system.dto;

import java.time.LocalDateTime;

public class EnrollmentResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private CourseResponseDTO course;
    private LocalDateTime enrollmentDate;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public StudentResponseDTO getStudent() {
        return student;
    }
    
    public void setStudent(StudentResponseDTO student) {
        this.student = student;
    }
    
    public CourseResponseDTO getCourse() {
        return course;
    }
    
    public void setCourse(CourseResponseDTO course) {
        this.course = course;
    }
    
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}