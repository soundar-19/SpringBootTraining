package com.student_course_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.student_course_management_system.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByRollNumber(Long rollNumber);
}
