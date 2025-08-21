package com.example.hostel_management_system.repository;

import com.example.hostel_management_system.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByEmail(String email, Pageable pageable);
    boolean existsByEmail(String email);
    List<Student> findByStaffId(Long staffId);
    
    // Task 3: Search with Paging
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Student> findByEmailEndingWith(String domain, Pageable pageable);
    Page<Student> findByAddressContainingIgnoreCase(String city, Pageable pageable);
    Page<Student> findByStaffIsNull(Pageable pageable);
    
    // Task 3: Advanced filtering with pagination
    @Query("SELECT s FROM Student s WHERE " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:address IS NULL OR LOWER(s.address) LIKE LOWER(CONCAT('%', :address, '%'))) AND " +
           "(:staffId IS NULL OR s.staff.id = :staffId)")
    Page<Student> findStudentsWithFilters(@Param("name") String name, 
                                         @Param("email") String email, 
                                         @Param("address") String address, 
                                         @Param("staffId") Long staffId, 
                                         Pageable pageable);
    
    // Task 7: Performance optimization using Slice
    @Query("SELECT s FROM Student s")
    Slice<Student> findAllSlice(Pageable pageable);
}