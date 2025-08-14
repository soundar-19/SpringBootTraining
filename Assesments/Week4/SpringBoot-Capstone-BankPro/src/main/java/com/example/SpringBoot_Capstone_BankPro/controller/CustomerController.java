package com.example.SpringBoot_Capstone_BankPro.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot_Capstone_BankPro.dto.CustomerRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.CustomerResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerResponseDTO> findAll(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return customerService.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.ok(customerService.create(customerRequestDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.ok(customerService.update(id, customerRequestDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponseDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.findByEmail(email));
    }
    
    @GetMapping("/search/name")
    public Page<CustomerResponseDTO> findByName(@RequestParam String name, @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return customerService.findByName(name, pageable);
    }
    
    @GetMapping("/search/email")
    public Page<CustomerResponseDTO> findByEmailContaining(@RequestParam String email, @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return customerService.findByEmailContaining(email, pageable);
    }
}
