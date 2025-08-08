package com.student_course_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_course_management_system.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseCode(String courseCode);

    Course findByCourseTitle(String courseTitle);

    List<Course> findAllByCredits(int credits);
}
