package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    
    @Autowired
    private StaffService staffService;
    

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDto> getStaffById(@PathVariable Long id) {
        StaffResponseDto responseDto = staffService.getStaffById(id);
        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<StaffResponseDto>> getAllStaff() {
        List<StaffResponseDto> staff = staffService.getAllStaff();
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/role/{role}")
    public ResponseEntity<List<StaffResponseDto>> getStaffByRole(@PathVariable Staff.StaffRole role) {
        List<StaffResponseDto> staff = staffService.getStaffByRole(role);
        return ResponseEntity.ok(staff);
    }
    

    @GetMapping("/{id}/students")
    public ResponseEntity<List<com.example.hostel_management_system.dto.StudentResponseDto>> getStudentsByStaffId(@PathVariable Long id) {
        List<com.example.hostel_management_system.dto.StudentResponseDto> students = staffService.getStudentsByStaffId(id);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/name/{name}")
    public ResponseEntity<List<StaffResponseDto>> getStaffByName(@PathVariable String name) {
        List<StaffResponseDto> staff = staffService.getStaffByNameContaining(name);
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/filter/email-domain/{domain}")
    public ResponseEntity<List<StaffResponseDto>> getStaffByEmailDomain(@PathVariable String domain) {
        List<StaffResponseDto> staff = staffService.getStaffByEmailDomain(domain);
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/filter/with-students")
    public ResponseEntity<List<StaffResponseDto>> getStaffWithStudents() {
        List<StaffResponseDto> staff = staffService.getStaffWithStudents();
        return ResponseEntity.ok(staff);
    }
}