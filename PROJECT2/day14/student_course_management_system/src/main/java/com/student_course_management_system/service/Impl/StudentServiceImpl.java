package com.student_course_management_system.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.repository.StudentRepository;
import com.student_course_management_system.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    


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
    

}
