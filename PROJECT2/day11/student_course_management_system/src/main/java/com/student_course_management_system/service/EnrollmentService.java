package com.student_course_management_system.service;

import java.util.List;

import com.student_course_management_system.domain.Enrollment;

public interface EnrollmentService {
    Enrollment save(Long studentId, Long courseId);
    List<Enrollment> findAll();
    Enrollment findById(Long id);
    void update(Long id, Enrollment enrollment);
    void delete(Long id);
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

}
