package com.student_course_management_system.service;

import java.util.List;

import com.student_course_management_system.domain.Student;

public interface StudentService {
    Student save(Student student);
    Student findById(Long id);
    Student update(Long id, Student student);
    void delete(Long id);
    List<Student> findAll();
    Student findByRollNumber(Long rollNumber);

}
