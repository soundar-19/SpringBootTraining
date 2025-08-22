package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    
    // Task 1: Multiple Sort Fields & Task 2: Default Paging
    @GetMapping
    public ResponseEntity<Page<StaffResponseDto>> getAllStaff(
            @PageableDefault(size = 10, sort = {"role", "name"}, 
                           direction = Sort.Direction.ASC) Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getAllStaff(pageable);
        return ResponseEntity.ok(staff);
    }
    
    // Task 3: Search with Paging
    @GetMapping("/role/{role}")
    public ResponseEntity<Page<StaffResponseDto>> getStaffByRole(
            @PathVariable Staff.StaffRole role,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getStaffByRole(role, pageable);
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<com.example.hostel_management_system.dto.StudentResponseDto>> getStudentsByStaffId(@PathVariable Long id) {
        List<com.example.hostel_management_system.dto.StudentResponseDto> students = staffService.getStudentsByStaffId(id);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/name/{name}")
    public ResponseEntity<Page<StaffResponseDto>> getStaffByName(
            @PathVariable String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getStaffByNameContaining(name, pageable);
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/filter/email-domain/{domain}")
    public ResponseEntity<Page<StaffResponseDto>> getStaffByEmailDomain(
            @PathVariable String domain,
            @PageableDefault(size = 10, sort = "email") Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getStaffByEmailDomain(domain, pageable);
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/filter/with-students")
    public ResponseEntity<Page<StaffResponseDto>> getStaffWithStudents(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getStaffWithStudents(pageable);
        return ResponseEntity.ok(staff);
    }
    
    // Admin-only endpoints
    @PostMapping("/admin")
    public ResponseEntity<StaffResponseDto> createStaff(@RequestBody Staff staff) {
        StaffResponseDto created = staffService.createStaff(staff);
        return ResponseEntity.ok(created);
    }
    
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}