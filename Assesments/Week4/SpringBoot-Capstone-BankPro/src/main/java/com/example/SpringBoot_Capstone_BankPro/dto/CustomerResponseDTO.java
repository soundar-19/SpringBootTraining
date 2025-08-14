package com.example.SpringBoot_Capstone_BankPro.dto;

import com.example.SpringBoot_Capstone_BankPro.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public static CustomerResponseDTO toDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getId(), customer.getName(),customer.getEmail(), customer.getPhone());
    }
}