package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warden")
public class WardenController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsUnderWarden(
            @PageableDefault(size = 15, sort = "name") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(students);
    }
    
    @PutMapping("/students/{id}/assign-staff")
    public ResponseEntity<StudentResponseDto> assignStaffToStudent(
            @PathVariable Long id, @RequestParam Long staffId) {
        StudentResponseDto updated = studentService.assignStaffToStudent(id, staffId);
        return ResponseEntity.ok(updated);
    }
}