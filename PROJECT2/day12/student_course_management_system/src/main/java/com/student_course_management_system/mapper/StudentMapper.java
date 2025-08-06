package com.student_course_management_system.mapper;

import org.springframework.stereotype.Component;
import com.student_course_management_system.domain.Student;
import com.student_course_management_system.dto.StudentRequestDTO;
import com.student_course_management_system.dto.StudentResponseDTO;

@Component
public class StudentMapper {
    public StudentResponseDTO toDTO(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setRollNumber(student.getRollNumber());
        studentResponseDTO.setEmail(student.getEmail());
        return studentResponseDTO;
    }
    public Student toEntity(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setRollNumber(studentRequestDTO.getRollNumber());
        student.setEmail(studentRequestDTO.getEmail());
        return student;
    }
}
