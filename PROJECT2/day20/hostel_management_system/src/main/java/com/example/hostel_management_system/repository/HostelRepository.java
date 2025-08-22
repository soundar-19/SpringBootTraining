package com.example.hostel_management_system.repository;

import com.example.hostel_management_system.domain.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {
    List<Hostel> findByHostelType(Hostel.HostelType hostelType);
    List<Hostel> findByNameContainingIgnoreCase(String name);
}