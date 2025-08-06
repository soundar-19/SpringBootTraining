package com.student_course_management_system.service;

import java.util.List;
import java.util.Set;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Student;

public interface CourseService {
    Course save(Course course);
    Course findById(Long courseId);
    Course update(Long courseId, Course course);
    void deleteById(Long courseId);
    List<Course> findAll();
    Course findByCourseCode(String courseCode);
    Course findByCourseTitle(String courseTitle);
    List<Course> findByCredits(int credits);
    Set<Student> getCourseStudents(Long courseId);
} 