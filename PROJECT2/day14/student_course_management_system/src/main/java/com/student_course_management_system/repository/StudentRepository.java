package com.student_course_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.student_course_management_system.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByRollNumber(Long rollNumber);
    
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.enrollments WHERE s.id = :id")
    Optional<Student> findByIdWithEnrollments(@Param("id") Long id);
}
