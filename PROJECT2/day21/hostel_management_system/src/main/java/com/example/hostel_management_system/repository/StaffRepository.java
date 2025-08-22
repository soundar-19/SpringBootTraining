package com.example.hostel_management_system.repository;

import com.example.hostel_management_system.domain.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);
    List<Staff> findByRole(Staff.StaffRole role);
    boolean existsByEmail(String email);
    
    // Pagination support
    Page<Staff> findByRole(Staff.StaffRole role, Pageable pageable);
    Page<Staff> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Staff> findByEmailEndingWith(String domain, Pageable pageable);
    
    @Query("SELECT s FROM Staff s WHERE SIZE(s.students) > 0")
    Page<Staff> findStaffWithStudents(Pageable pageable);
}