package com.example.hostel_management_system.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hostels")
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String address;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private HostelType hostelType;
    
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Room> rooms;
    
    public Hostel() {}
    
    public Hostel(String name, String address, HostelType hostelType) {
        this.name = name;
        this.address = address;
        this.hostelType = hostelType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public HostelType getHostelType() { return hostelType; }
    public void setHostelType(HostelType hostelType) { this.hostelType = hostelType; }
    
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
    
    public enum HostelType {
        BOYS, GIRLS, CO_ED
    }
}