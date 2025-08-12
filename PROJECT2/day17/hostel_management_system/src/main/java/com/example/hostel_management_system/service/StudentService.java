package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.StudentRepository;
import com.example.hostel_management_system.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

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
    
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toResponseDto);
    }
    

    
    public Page<StudentResponseDto> getStudentsByNameContaining(String name, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(studentMapper::toResponseDto);
    }
    
    public Page<StudentResponseDto> getStudentsByEmailDomain(String domain, Pageable pageable) {
        return studentRepository.findByEmailEndingWith(domain, pageable)
                .map(studentMapper::toResponseDto);
    }
    
    public Page<StudentResponseDto> getStudentsByAddressCity(String city, Pageable pageable) {
        return studentRepository.findByAddressContainingIgnoreCase(city, pageable)
                .map(studentMapper::toResponseDto);
    }
    
    public Page<StudentResponseDto> filterStudents(String name, String email, String address, Long staffId, Pageable pageable) {
        return studentRepository.findStudentsWithFilters(name, email, address, staffId, pageable)
                .map(studentMapper::toResponseDto);
    }
    
    public Page<StudentResponseDto> getStudentsWithoutStaff(Pageable pageable) {
        return studentRepository.findByStaffIsNull(pageable)
                .map(studentMapper::toResponseDto);
    }
    
    // Task 4: Return Only Paged Metadata
    public Map<String, Object> getStudentsMetadata(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("totalElements", page.getTotalElements());
        metadata.put("totalPages", page.getTotalPages());
        metadata.put("currentPage", page.getNumber());
        metadata.put("pageSize", page.getSize());
        metadata.put("hasNext", page.hasNext());
        metadata.put("hasPrevious", page.hasPrevious());
        metadata.put("isFirst", page.isFirst());
        metadata.put("isLast", page.isLast());
        return metadata;
    }
    
    // Task 5: Custom Sort with Null Handling
    public Page<StudentResponseDto> getStudentsSortedNullsLast(Pageable pageable) {
        Sort sort = Sort.by(Sort.Order.asc("name").nullsLast(), Sort.Order.desc("email").nullsFirst());
        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return studentRepository.findAll(customPageable).map(studentMapper::toResponseDto);
    }
    
    // Task 7: Performance Check - Using Slice
    public Slice<StudentResponseDto> getStudentsSlice(Pageable pageable) {
        return studentRepository.findAllSlice(pageable).map(studentMapper::toResponseDto);
    }
    

}