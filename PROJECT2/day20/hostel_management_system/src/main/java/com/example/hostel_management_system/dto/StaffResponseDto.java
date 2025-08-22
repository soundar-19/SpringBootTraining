package com.example.hostel_management_system.dto;

import com.example.hostel_management_system.domain.Staff;

public class StaffResponseDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Staff.StaffRole role;
    private Integer studentCount;
    
    public StaffResponseDto() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Staff.StaffRole getRole() { return role; }
    public void setRole(Staff.StaffRole role) { this.role = role; }
    
    public Integer getStudentCount() { return studentCount; }
    public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }
}