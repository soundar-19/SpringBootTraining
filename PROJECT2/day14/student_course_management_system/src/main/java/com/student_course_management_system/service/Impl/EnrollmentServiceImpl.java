package com.student_course_management_system.service.Impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.dto.EnrollmentRequestDTO;
import com.student_course_management_system.exception.DuplicateResourceException;
import com.student_course_management_system.exception.ResourceNotFoundException;
import com.student_course_management_system.repository.CourseRepository;
import com.student_course_management_system.repository.EnrollmentRepository;
import com.student_course_management_system.repository.StudentRepository;
import com.student_course_management_system.service.EnrollmentService;

@Service
@Transactional
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

    @Override
    public Enrollment enrollStudent(EnrollmentRequestDTO enrollmentRequest) {
        Student student = studentRepository.findById(enrollmentRequest.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollmentRequest.getStudentId()));
        
        Course course = courseRepository.findById(enrollmentRequest.getCourseId())
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentRequest.getCourseId()));
        
        Optional<Enrollment> existingEnrollment = enrollmentRepository
            .findByStudentIdAndCourseId(enrollmentRequest.getStudentId(), enrollmentRequest.getCourseId());
        
        if (existingEnrollment.isPresent()) {
            throw new DuplicateResourceException("Student is already enrolled in this course");
        }
        
        Enrollment enrollment = new Enrollment(student, course);
        enrollment.setSemester(enrollmentRequest.getSemester());
        enrollment.setYear(enrollmentRequest.getYear());
        
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public boolean unenrollStudent(Long studentId, Long courseId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        
        if (enrollment.isPresent()) {
            enrollmentRepository.delete(enrollment.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentIdWithCourse(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> getCourseEnrollments(Long courseId) {
        return enrollmentRepository.findByCourseIdWithStudent(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment updateGrade(Long enrollmentId, String grade) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        
        enrollment.setGrade(grade);
        return enrollmentRepository.save(enrollment);
    }
}