package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StaffService;
import com.example.hostel_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> dashboard = staffService.getAdminDashboard();
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/staff/all")
    public ResponseEntity<Page<StaffResponseDto>> getAllStaffForAdmin(
            @PageableDefault(size = 20, sort = "role") Pageable pageable) {
        Page<StaffResponseDto> staff = staffService.getAllStaff(pageable);
        return ResponseEntity.ok(staff);
    }
    
    @GetMapping("/students/all")
    public ResponseEntity<Page<StudentResponseDto>> getAllStudentsForAdmin(
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(students);
    }
}