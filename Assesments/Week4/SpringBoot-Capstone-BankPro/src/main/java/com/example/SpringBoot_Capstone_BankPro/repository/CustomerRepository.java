package com.example.SpringBoot_Capstone_BankPro.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBoot_Capstone_BankPro.domain.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findByEmail(String email);
    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Customer> findByEmailContainingIgnoreCase(String email, Pageable pageable);
} 