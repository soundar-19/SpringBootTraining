package com.student_course_management_system.service;

import java.util.List;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.domain.Student;

public interface EnrollmentService {
    Enrollment enrollStudent(Long studentId, Long courseId);
    void unenrollStudent(Long studentId, Long courseId);
    List<Course> getStudentCourses(Long studentId);
    List<Student> getCourseStudents(Long courseId);
    List<Enrollment> getAllEnrollments();
}