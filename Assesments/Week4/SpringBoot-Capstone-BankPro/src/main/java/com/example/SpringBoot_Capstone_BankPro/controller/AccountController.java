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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.AccountResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Page<AccountResponseDTO> findAll(@PageableDefault(size = 10, sort = "accountNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        return accountService.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }
    
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> findByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }
    
    @GetMapping("/customer/{customerId}")
    public Page<AccountResponseDTO> findByCustomerId(@PathVariable Long customerId, @PageableDefault(size = 10, sort = "accountNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        return accountService.findByCustomerId(customerId, pageable);
    }
    
    @GetMapping("/type/{accountType}")
    public Page<AccountResponseDTO> findByAccountType(@PathVariable AccountType accountType, @PageableDefault(size = 10, sort = "accountNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        return accountService.findByAccountType(accountType, pageable);
    }
    
    @GetMapping("/balance")
    public Page<AccountResponseDTO> findByMinBalance(@RequestParam Double minBalance, @PageableDefault(size = 10, sort = "balance", direction = Sort.Direction.DESC) Pageable pageable) {
        return accountService.findByMinBalance(minBalance, pageable);
    }
    
    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok(accountService.create(accountRequestDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok(accountService.update(id, accountRequestDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
