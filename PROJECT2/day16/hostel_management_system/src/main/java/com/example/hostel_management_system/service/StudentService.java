package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.StudentRepository;
import com.example.hostel_management_system.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StudentMapper studentMapper;
    
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return studentMapper.toResponseDto(student);
    }
    
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    

    
    public List<StudentResponseDto> getStudentsByNameContaining(String name) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StudentResponseDto> getStudentsByEmailDomain(String domain) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getEmail().endsWith(domain))
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StudentResponseDto> getStudentsByAddressCity(String city) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAddress() != null && student.getAddress().toLowerCase().contains(city.toLowerCase()))
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StudentResponseDto> filterStudents(String name, String email, String address, Long staffId) {
        return studentRepository.findAll().stream()
                .filter(student -> name == null || student.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(student -> email == null || student.getEmail().toLowerCase().contains(email.toLowerCase()))
                .filter(student -> address == null || (student.getAddress() != null && student.getAddress().toLowerCase().contains(address.toLowerCase())))
                .filter(student -> staffId == null || (student.getStaff() != null && student.getStaff().getId().equals(staffId)))
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StudentResponseDto> getStudentsWithoutStaff() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getStaff() == null)
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    

}