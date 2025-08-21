package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Hostel;
import com.example.hostel_management_system.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hostels")
public class HostelController {
    
    @Autowired
    private HostelService hostelService;
    

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id) {
        Hostel hostel = hostelService.getHostelById(id);
        return ResponseEntity.ok(hostel);
    }
    
    @GetMapping
    public ResponseEntity<List<Hostel>> getAllHostels() {
        List<Hostel> hostels = hostelService.getAllHostels();
        return ResponseEntity.ok(hostels);
    }
    
    @GetMapping("/type/{hostelType}")
    public ResponseEntity<List<Hostel>> getHostelsByType(@PathVariable Hostel.HostelType hostelType) {
        List<Hostel> hostels = hostelService.getHostelsByType(hostelType);
        return ResponseEntity.ok(hostels);
    }
    

}