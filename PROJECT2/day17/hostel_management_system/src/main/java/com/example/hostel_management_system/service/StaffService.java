package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.StaffRepository;
import com.example.hostel_management_system.mapper.StaffMapper;
import com.example.hostel_management_system.mapper.StudentMapper;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StaffMapper staffMapper;
    
    @Autowired
    private StudentMapper studentMapper;
    
    public StaffResponseDto getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", id));
        return staffMapper.toResponseDto(staff);
    }
    
    public Page<StaffResponseDto> getAllStaff(Pageable pageable) {
        return staffRepository.findAll(pageable).map(staffMapper::toResponseDto);
    }
    
    public Page<StaffResponseDto> getStaffByRole(Staff.StaffRole role, Pageable pageable) {
        return staffRepository.findByRole(role, pageable).map(staffMapper::toResponseDto);
    }
    

    
    public List<StudentResponseDto> getStudentsByStaffId(Long staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new ResourceNotFoundException("Staff", "id", staffId);
        }
        return studentRepository.findByStaffId(staffId).stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public Page<StaffResponseDto> getStaffByNameContaining(String name, Pageable pageable) {
        return staffRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(staffMapper::toResponseDto);
    }
    
    public Page<StaffResponseDto> getStaffByEmailDomain(String domain, Pageable pageable) {
        return staffRepository.findByEmailEndingWith(domain, pageable)
                .map(staffMapper::toResponseDto);
    }
    
    public Page<StaffResponseDto> getStaffWithStudents(Pageable pageable) {
        return staffRepository.findStaffWithStudents(pageable).map(staffMapper::toResponseDto);
    }
}