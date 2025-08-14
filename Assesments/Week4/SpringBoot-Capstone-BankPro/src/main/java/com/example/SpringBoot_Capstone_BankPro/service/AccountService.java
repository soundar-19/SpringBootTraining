package com.example.SpringBoot_Capstone_BankPro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountResponseDTO;


public interface AccountService {
    AccountResponseDTO findById(Long id);
    AccountResponseDTO findByAccountNumber(String accountNumber);
    AccountResponseDTO create(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO);
    void delete(Long id);
    void deleteByAccountNumber(String accountNumber);
    Page<AccountResponseDTO> findAll(Pageable pageable);
    Page<AccountResponseDTO> findByCustomerId(Long customerId, Pageable pageable);
    Page<AccountResponseDTO> findByAccountType(AccountType accountType, Pageable pageable);
    Page<AccountResponseDTO> findByMinBalance(Double minBalance, Pageable pageable);
}
