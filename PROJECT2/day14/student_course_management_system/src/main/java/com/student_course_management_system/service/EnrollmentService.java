package com.student_course_management_system.service;

import java.util.List;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.dto.EnrollmentRequestDTO;

public interface EnrollmentService {
    Enrollment enrollStudent(EnrollmentRequestDTO enrollmentRequest);
    boolean unenrollStudent(Long studentId, Long courseId);
    List<Enrollment> getStudentEnrollments(Long studentId);
    List<Enrollment> getCourseEnrollments(Long courseId);
    List<Enrollment> getAllEnrollments();
    Enrollment updateGrade(Long enrollmentId, String grade);
}