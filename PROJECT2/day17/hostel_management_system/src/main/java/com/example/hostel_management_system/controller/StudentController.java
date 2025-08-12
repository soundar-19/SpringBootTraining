package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
    
    // Task 1: Multiple Sort Fields & Task 2: Default Paging
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/name/{name}")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsByName(
            @PathVariable String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getStudentsByNameContaining(name, pageable);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/email-domain/{domain}")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsByEmailDomain(
            @PathVariable String domain,
            @PageableDefault(size = 10, sort = "email") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getStudentsByEmailDomain(domain, pageable);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/city/{city}")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsByCity(
            @PathVariable String city,
            @PageableDefault(size = 10, sort = "address") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getStudentsByAddressCity(city, pageable);
        return ResponseEntity.ok(students);
    }
    
    // Task 3: Search with Paging
    @GetMapping("/filter")
    public ResponseEntity<Page<StudentResponseDto>> filterStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Long staffId,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.filterStudents(name, email, address, staffId, pageable);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/filter/without-staff")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsWithoutStaff(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getStudentsWithoutStaff(pageable);
        return ResponseEntity.ok(students);
    }
    
    // Task 4: Return Only Paged Metadata
    @GetMapping("/metadata")
    public ResponseEntity<Map<String, Object>> getStudentsMetadata(
            @PageableDefault(size = 10) Pageable pageable) {
        Map<String, Object> metadata = studentService.getStudentsMetadata(pageable);
        return ResponseEntity.ok(metadata);
    }
    
    // Task 5: Custom Sort with Null Handling
    @GetMapping("/sorted-nulls-last")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsSortedNullsLast(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentResponseDto> students = studentService.getStudentsSortedNullsLast(pageable);
        return ResponseEntity.ok(students);
    }
    
    // Task 7: Performance Check - Using Slice instead of Page
    @GetMapping("/slice")
    public ResponseEntity<Slice<StudentResponseDto>> getStudentsSlice(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Slice<StudentResponseDto> students = studentService.getStudentsSlice(pageable);
        return ResponseEntity.ok(students);
    }
}