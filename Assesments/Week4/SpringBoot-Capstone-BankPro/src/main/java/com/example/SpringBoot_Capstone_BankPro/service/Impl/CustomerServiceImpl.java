package com.example.SpringBoot_Capstone_BankPro.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SpringBoot_Capstone_BankPro.domain.Customer;
import com.example.SpringBoot_Capstone_BankPro.dto.CustomerRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.CustomerResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.exception.ResourceNotFoundException;
import com.example.SpringBoot_Capstone_BankPro.repository.CustomerRepository;
import com.example.SpringBoot_Capstone_BankPro.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) throw new ResourceNotFoundException("There is no customer with id: " + id);
        return CustomerResponseDTO.toDTO(customer);
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRequestDTO.toEntity();
        return CustomerResponseDTO.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPhone(customerRequestDTO.getPhone());
        
        return CustomerResponseDTO.toDTO(customerRepository.save(customer));
    }

    @Override
    public Page<CustomerResponseDTO> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerResponseDTO::toDTO);
    }

    @Override
    public void delete(Long id) {
            customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDTO findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
        return CustomerResponseDTO.toDTO(customer);
    }

    @Override
    public Page<CustomerResponseDTO> findByName(String name, Pageable pageable) {
        return customerRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(CustomerResponseDTO::toDTO);
    }

    @Override
    public Page<CustomerResponseDTO> findByEmailContaining(String email, Pageable pageable) {
        return customerRepository.findByEmailContainingIgnoreCase(email, pageable)
            .map(CustomerResponseDTO::toDTO);
    }
    
}
