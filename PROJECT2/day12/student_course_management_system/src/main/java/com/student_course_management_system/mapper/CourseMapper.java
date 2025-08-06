package com.student_course_management_system.mapper;

import org.springframework.stereotype.Component;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.dto.CourseRequestDTO;
import com.student_course_management_system.dto.CourseResponseDTO;

@Component
public class CourseMapper {
    public CourseResponseDTO toDTO(Course course) {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setCourseTitle(course.getCourseTitle());
        courseResponseDTO.setCourseCode(course.getCourseCode());
        courseResponseDTO.setCredits(course.getCredits());
        return courseResponseDTO;
    }
    public Course toEntity(CourseRequestDTO courseRequestDTO) {
        Course course = new Course();
        course.setCourseTitle(courseRequestDTO.getCourseTitle());
        course.setCourseCode(courseRequestDTO.getCourseCode());
        course.setCredits(courseRequestDTO.getCredits());
        return course;
    }
}
