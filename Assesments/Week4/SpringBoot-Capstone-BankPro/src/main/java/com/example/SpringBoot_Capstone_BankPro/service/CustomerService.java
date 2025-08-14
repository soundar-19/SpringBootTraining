package com.example.SpringBoot_Capstone_BankPro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.SpringBoot_Capstone_BankPro.dto.CustomerRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.CustomerResponseDTO;

public interface CustomerService {
    CustomerResponseDTO findById(Long id);
    CustomerResponseDTO findByEmail(String email);
    CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO update(Long id, CustomerRequestDTO customerRequestDTO);
    Page<CustomerResponseDTO> findAll(Pageable pageable);
    Page<CustomerResponseDTO> findByName(String name, Pageable pageable);
    Page<CustomerResponseDTO> findByEmailContaining(String email, Pageable pageable);
    void delete(Long id);
}
