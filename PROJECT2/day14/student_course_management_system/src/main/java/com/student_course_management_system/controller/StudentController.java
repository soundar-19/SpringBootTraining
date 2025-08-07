package com.student_course_management_system.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.dto.CourseResponseDTO;
import com.student_course_management_system.dto.StudentRequestDTO;
import com.student_course_management_system.dto.StudentResponseDTO;
import com.student_course_management_system.exception.DuplicateResourceException;
import com.student_course_management_system.exception.InvalidInputException;
import com.student_course_management_system.exception.ResourceNotFoundException;
import com.student_course_management_system.mapper.CourseMapper;
import com.student_course_management_system.mapper.StudentMapper;
import com.student_course_management_system.service.StudentService;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {
    
    private StudentService studentService;
    private StudentMapper studentMapper;
    private CourseMapper courseMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper, CourseMapper courseMapper){
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO student) {
        Student savedStudent = studentService.save(studentMapper.toEntity(student));
        if(savedStudent == null) throw new DuplicateResourceException("Student with this roll number already exists");
        return ResponseEntity.ok(studentMapper.toDTO(savedStudent));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> response = new ArrayList<>();
        for(Student student : studentService.findAll()){
            response.add(studentMapper.toDTO(student));
        }
        if(response.isEmpty()) throw new ResourceNotFoundException("No Students Record Found");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable @Positive(message = "Student ID must be positive") Long id) {
        Student student = studentService.findById(id);
        if(student == null) throw new ResourceNotFoundException("Student not found");
        return ResponseEntity.ok(studentMapper.toDTO(student));
    }

    @GetMapping("/rollNumber/{rollNumber}")
    public ResponseEntity<StudentResponseDTO> getStudentByRollNumber(@PathVariable @Positive(message = "Roll number must be positive") Long rollNumber) {
        Student student = studentService.findByRollNumber(rollNumber);
        if(student == null) throw new ResourceNotFoundException("Student not found");
        return ResponseEntity.ok(studentMapper.toDTO(student));    
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable @Positive(message = "Student ID must be positive") Long id, 
            @Valid @RequestBody StudentRequestDTO student) {
        Student updatedStudent = studentService.update(id, studentMapper.toEntity(student));
        if(updatedStudent == null) throw new ResourceNotFoundException("Student not found");
        return ResponseEntity.ok(studentMapper.toDTO(updatedStudent));    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable @Positive(message = "Student ID must be positive") Long id) {
        if(studentService.findById(id) == null) throw new ResourceNotFoundException("Student not found");
        studentService.delete(id);
        return ResponseEntity.ok("Student Deleted Successfully!");
    }
    
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<StudentResponseDTO> enrollInCourse(
            @PathVariable @Positive(message = "Student ID must be positive") Long studentId, 
            @PathVariable @Positive(message = "Course ID must be positive") Long courseId) {
        Student student = studentService.enrollInCourse(studentId, courseId);
        if(student == null) throw new DuplicateResourceException("Student already enrolled in this course");
        return ResponseEntity.ok(studentMapper.toDTO(student));    
    }
    
    @DeleteMapping("/{studentId}/unenroll/{courseId}")
    public ResponseEntity<StudentResponseDTO> unenrollFromCourse(
            @PathVariable @Positive(message = "Student ID must be positive") Long studentId, 
            @PathVariable @Positive(message = "Course ID must be positive") Long courseId) {
        Student student = studentService.unenrollFromCourse(studentId, courseId);
        if(student == null) throw new InvalidInputException("Invalid input");
        return ResponseEntity.ok(studentMapper.toDTO(student));
    }
    
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<CourseResponseDTO>> getStudentCourses(
            @PathVariable @Positive(message = "Student ID must be positive") Long studentId) {
        Set<Course> courses = studentService.getStudentCourses(studentId);
        if(courses.isEmpty()) throw new ResourceNotFoundException("No courses found");
        Set<CourseResponseDTO> response = new HashSet<>();
        for(Course course : courses){
            response.add(courseMapper.toDTO(course));
        }
        return ResponseEntity.ok(response);
    }
}