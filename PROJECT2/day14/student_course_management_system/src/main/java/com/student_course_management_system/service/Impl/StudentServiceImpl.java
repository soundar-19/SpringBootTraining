package com.student_course_management_system.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.student_course_management_system.domain.Course;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.repository.CourseRepository;
import com.student_course_management_system.repository.StudentRepository;
import com.student_course_management_system.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public Student save(Student student){
        if(studentRepository.findByRollNumber(student.getRollNumber())!= null) return null;
        return studentRepository.save(student);
    }
    public Student findById(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    public Student update(Long id, Student student){

    Student existingWithRollNumber = studentRepository.findByRollNumber(student.getRollNumber());
    if (existingWithRollNumber != null && !existingWithRollNumber.getId().equals(id)) {
        return null; 
    }
    
    return studentRepository.findById(id)
        .map(existingStudent -> {
            existingStudent.setName(student.getName());
            existingStudent.setRollNumber(student.getRollNumber());
            existingStudent.setEmail(student.getEmail());
            return studentRepository.save(existingStudent);
        }).orElse(null);
}        
    public void delete(Long id){
        if(findById(id) != null) studentRepository.deleteById(id);
    }
    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    public Student findByRollNumber(Long rollNumber){
        return studentRepository.findByRollNumber(rollNumber);
    }
    
    @Transactional
    public Student enrollInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findByIdWithCourses(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        
        if (student == null || course == null) return null;
        if (student.getCourses().contains(course)) return null;
        
        student.getCourses().add(course);
        return studentRepository.save(student);
    }
    
    @Transactional
    public Student unenrollFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findByIdWithCourses(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        
        if (student == null || course == null) return null;
        
        student.getCourses().remove(course);
        return studentRepository.save(student);
    }
    
    public Set<Course> getStudentCourses(Long studentId) {
        Student student = studentRepository.findByIdWithCourses(studentId).orElse(null);
        return student != null ? student.getCourses() : null;
    }
}
