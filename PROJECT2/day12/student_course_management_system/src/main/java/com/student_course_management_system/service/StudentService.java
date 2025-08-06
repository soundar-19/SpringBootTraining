package com.student_course_management_system.service;

import java.util.List;
import java.util.Set;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Student;

public interface StudentService {
    Student save(Student student);
    Student findById(Long id);
    Student update(Long id, Student student);
    void delete(Long id);
    List<Student> findAll();
    Student findByRollNumber(Long rollNumber);
    Student enrollInCourse(Long studentId, Long courseId);
    Student unenrollFromCourse(Long studentId, Long courseId);
    Set<Course> getStudentCourses(Long studentId);
}
