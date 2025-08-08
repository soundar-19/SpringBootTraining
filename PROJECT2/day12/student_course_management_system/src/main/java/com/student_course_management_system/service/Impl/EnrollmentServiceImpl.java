package com.student_course_management_system.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.repository.CourseRepository;
import com.student_course_management_system.repository.EnrollmentRepository;
import com.student_course_management_system.repository.StudentRepository;
import com.student_course_management_system.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, 
                               StudentRepository studentRepository, 
                               CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        
        if (student == null || course == null) return null;
        
        if (enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent()) {
            return null;
        }
        
        Enrollment enrollment = new Enrollment(student, course);
        return enrollmentRepository.save(enrollment);
    }
    
    @Transactional
    public void unenrollStudent(Long studentId, Long courseId) {
        enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
            .ifPresent(enrollmentRepository::delete);
    }
    
    public List<Course> getStudentCourses(Long studentId) {
        return enrollmentRepository.findByStudentIdWithCourse(studentId)
            .stream()
            .map(Enrollment::getCourse)
            .collect(Collectors.toList());
    }
    
    public List<Student> getCourseStudents(Long courseId) {
        return enrollmentRepository.findByCourseIdWithStudent(courseId)
            .stream()
            .map(Enrollment::getStudent)
            .collect(Collectors.toList());
    }
    
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}