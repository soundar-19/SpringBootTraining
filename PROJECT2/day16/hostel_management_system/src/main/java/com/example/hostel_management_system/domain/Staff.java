package com.example.hostel_management_system.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String address;
    
    @Enumerated(EnumType.STRING)
    private StaffRole role;
    
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Student> students;
    
    public Staff() {}
    
    public Staff(String name, String email, StaffRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public StaffRole getRole() { return role; }
    public void setRole(StaffRole role) { this.role = role; }
    
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
    
    public enum StaffRole {
        WARDEN, ASSISTANT_WARDEN, SECURITY, MAINTENANCE, ADMIN
    }
}