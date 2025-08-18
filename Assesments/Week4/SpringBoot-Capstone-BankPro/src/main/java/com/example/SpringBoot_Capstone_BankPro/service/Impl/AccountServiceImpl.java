package com.example.SpringBoot_Capstone_BankPro.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;
import com.example.SpringBoot_Capstone_BankPro.domain.Customer;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.exception.ResourceNotFoundException;
import com.example.SpringBoot_Capstone_BankPro.repository.AccountRepository;
import com.example.SpringBoot_Capstone_BankPro.repository.CustomerRepository;
import com.example.SpringBoot_Capstone_BankPro.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    public AccountServiceImpl(AccountRepository accountRepository,CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AccountResponseDTO findById(Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Account with id: " + id + " does not exist"));
        return AccountResponseDTO.toDTO(account);
    }

    @Override
    public AccountResponseDTO create(AccountRequestDTO accountRequestDTO) {
        Account account = accountRequestDTO.toEntity();
        Long customerId = accountRequestDTO.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + customerId + " does not exist"));
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);
        return AccountResponseDTO.toDTO(savedAccount);
    }

    @Override
    public Page<AccountResponseDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(AccountResponseDTO::toDTO);
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Account with id: " + id + " does not exist"));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Account with id: " + id + " does not exist"));
        account.setAccountNumber(accountRequestDTO.getAccountNumber());
        account.setBalance(accountRequestDTO.getBalance());
        Account updatedAccount = accountRepository.save(account);
        return AccountResponseDTO.toDTO(updatedAccount);
    }

    @Override
    public AccountResponseDTO findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("No account with account number: " + accountNumber));
        return AccountResponseDTO.toDTO(account);
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account with account number: " + accountNumber + " does not exist"));
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public Page<AccountResponseDTO> findByCustomerId(Long customerId, Pageable pageable) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + customerId + " does not exist"));
        return accountRepository.findByCustomer(customer, pageable)
            .map(AccountResponseDTO::toDTO);
    }

    @Override
    public Page<AccountResponseDTO> findByAccountType(AccountType accountType, Pageable pageable) {
        return accountRepository.findByAccountType(accountType, pageable)
            .map(AccountResponseDTO::toDTO);
    }

    @Override
    public Page<AccountResponseDTO> findByMinBalance(Double minBalance, Pageable pageable) {
        return accountRepository.findByBalanceGreaterThan(minBalance, pageable)
            .map(AccountResponseDTO::toDTO);
    }
    
}
