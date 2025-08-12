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
    
    public List<StaffResponseDto> getAllStaff() {
        return staffRepository.findAll().stream()
                .map(staffMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StaffResponseDto> getStaffByRole(Staff.StaffRole role) {
        return staffRepository.findByRole(role).stream()
                .map(staffMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    

    
    public List<StudentResponseDto> getStudentsByStaffId(Long staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new ResourceNotFoundException("Staff", "id", staffId);
        }
        return studentRepository.findByStaffId(staffId).stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StaffResponseDto> getStaffByNameContaining(String name) {
        return staffRepository.findAll().stream()
                .filter(staff -> staff.getName().toLowerCase().contains(name.toLowerCase()))
                .map(staffMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StaffResponseDto> getStaffByEmailDomain(String domain) {
        return staffRepository.findAll().stream()
                .filter(staff -> staff.getEmail().endsWith(domain))
                .map(staffMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<StaffResponseDto> getStaffWithStudents() {
        return staffRepository.findAll().stream()
                .filter(staff -> staff.getStudents() != null && !staff.getStudents().isEmpty())
                .map(staffMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}