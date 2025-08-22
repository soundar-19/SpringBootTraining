package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Hostel;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostelService {
    
    @Autowired
    private HostelRepository hostelRepository;
    
    
    public Hostel getHostelById(Long id) {
        return hostelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hostel", "id", id));
    }
    
    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }
    
    public List<Hostel> getHostelsByType(Hostel.HostelType hostelType) {
        return hostelRepository.findByHostelType(hostelType);
    }
    

}