package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto responseDto = studentService.getStudentById(id);
        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    

    @GetMapping("/filter/name/{name}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByName(@PathVariable String name) {
        List<StudentResponseDto> students = studentService.getStudentsByNameContaining(name);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/email-domain/{domain}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByEmailDomain(@PathVariable String domain) {
        List<StudentResponseDto> students = studentService.getStudentsByEmailDomain(domain);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/city/{city}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByCity(@PathVariable String city) {
        List<StudentResponseDto> students = studentService.getStudentsByAddressCity(city);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDto>> filterStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Long staffId) {
        List<StudentResponseDto> students = studentService.filterStudents(name, email, address, staffId);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/without-staff")
    public ResponseEntity<List<StudentResponseDto>> getStudentsWithoutStaff() {
        List<StudentResponseDto> students = studentService.getStudentsWithoutStaff();
        return ResponseEntity.ok(students);
    }
}