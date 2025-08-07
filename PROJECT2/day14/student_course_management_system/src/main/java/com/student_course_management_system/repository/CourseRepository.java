package com.student_course_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student_course_management_system.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseCode(String courseCode);

    Course findByCourseTitle(String courseTitle);

    List<Course> findAllByCredits(int credits);
    
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students WHERE c.id = :id")
    Optional<Course> findByIdWithStudents(@Param("id") Long id);
}
