package com.student_course_management_system.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.repository.CourseRepository;
import com.student_course_management_system.repository.EnrollmentRepository;
import com.student_course_management_system.repository.StudentRepository;
import com.student_course_management_system.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public Enrollment save(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if(student != null && course != null) {
            if(enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) return null;
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrollmentDate(LocalDate.now());
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }
    
    public List<Enrollment> findAll(){
        return enrollmentRepository.findAll();
    }
    
    public Enrollment findById(Long id){
        return enrollmentRepository.findById(id).orElse(null);
    }
    
    public void update(Long id, Enrollment enrollment) {
        if(enrollmentRepository.existsById(id)) {
            enrollment.setId(id);
            enrollmentRepository.save(enrollment);
        }
    }
    
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
    
    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
    
    public List<Enrollment> findByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
    
    public void deleteByStudentIdAndCourseId(Long studentId, Long courseId) {
        enrollmentRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }
    
    public boolean existsByStudentIdAndCourseId(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }


}
