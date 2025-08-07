package com.student_course_management_system.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.dto.CourseRequestDTO;
import com.student_course_management_system.dto.CourseResponseDTO;
import com.student_course_management_system.dto.StudentResponseDTO;
import com.student_course_management_system.exception.DuplicateResourceException;
import com.student_course_management_system.exception.ResourceNotFoundException;
import com.student_course_management_system.mapper.CourseMapper;
import com.student_course_management_system.mapper.StudentMapper;
import com.student_course_management_system.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {

    private CourseService courseService;
    private CourseMapper courseMapper;
    private StudentMapper studentMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper, StudentMapper studentMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO course) {
        Course savedCourse = courseService.save(courseMapper.toEntity(course));
        if (savedCourse == null) throw new DuplicateResourceException("Course with this code or title already exists");
        return ResponseEntity.ok(courseMapper.toDTO(savedCourse));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> response = new ArrayList<>();
        for(Course course : courseService.findAll()){
            response.add(courseMapper.toDTO(course));
        }
        if(response.isEmpty()) throw new ResourceNotFoundException("No courses found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable @Positive(message = "Course ID must be positive") Long id) {
        Course course = courseService.findById(id);
        if(course == null) throw new ResourceNotFoundException("Course not found");
        return ResponseEntity.ok(courseMapper.toDTO(course));
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<CourseResponseDTO> getCourseByCourseCode(
            @PathVariable @NotBlank(message = "Course code cannot be blank") 
            @Pattern(regexp = "^[A-Z]{2,4}[0-9]{3,6}$", message = "Invalid course code format") String courseCode) {
        Course course = courseService.findByCourseCode(courseCode);
        if(course == null) throw new ResourceNotFoundException("Course not found");
        return ResponseEntity.ok(courseMapper.toDTO(course));
    }

    @GetMapping("/title/{courseTitle}")
    public ResponseEntity<CourseResponseDTO> getCourseByCourseTitle(
            @PathVariable @NotBlank(message = "Course title cannot be blank") String courseTitle) {
        Course course = courseService.findByCourseTitle(courseTitle);
        if(course == null) throw new ResourceNotFoundException("Course not found");
        return ResponseEntity.ok(courseMapper.toDTO(course));
    }

    @GetMapping("/credits/{credits}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByCredits(
            @PathVariable @Min(value = 1, message = "Credits must be at least 1") int credits) {
        List<CourseResponseDTO> response = new ArrayList<>();
        for(Course course : courseService.findByCredits(credits)){
            response.add(courseMapper.toDTO(course));
        }
        if(response.isEmpty()) throw new ResourceNotFoundException("No courses found");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable @Positive(message = "Course ID must be positive") Long id, 
            @Valid @RequestBody CourseRequestDTO course) {
        Course updatedCourse = courseService.update(id, courseMapper.toEntity(course));
        if(updatedCourse == null) throw new ResourceNotFoundException("Course not found");
        return ResponseEntity.ok(courseMapper.toDTO(updatedCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable @Positive(message = "Course ID must be positive") Long id) {
        if(courseService.findById(id) == null) throw new ResourceNotFoundException("Course not found");
        courseService.deleteById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
    
    @GetMapping("/{courseId}/students")
    public ResponseEntity<Set<StudentResponseDTO>> getCourseStudents(
            @PathVariable @Positive(message = "Course ID must be positive") Long courseId) {
        Set<Student> students = courseService.getCourseStudents(courseId);
        if(students.isEmpty()) throw new ResourceNotFoundException("No students found");
        Set<StudentResponseDTO> response = new HashSet<>();
        for(Student student : students){
            response.add(studentMapper.toDTO(student));
        }
        return ResponseEntity.ok(response);
    }
}