package com.example.SpringBoot_Capstone_BankPro.controller;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.TransferRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Page<TransactionResponseDTO> findAll(@PageableDefault(size = 10, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        return ResponseEntity.ok(transactionService.Deposit(transactionRequestDTO));
    }
    
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> withdraw(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        return ResponseEntity.ok(transactionService.Withdraw(transactionRequestDTO));
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid TransferRequestDTO transferRequestDTO) {
        transactionService.Transfer(transferRequestDTO.getSenderAC(),transferRequestDTO.getReceiverAC(),transferRequestDTO.getAmount());
        return ResponseEntity.ok("Transfer Successful");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
    @GetMapping("/{accountNumber}/transactions")
    public Page<TransactionResponseDTO> findTransactionsByAccountNumber(@PathVariable String accountNumber, @PageableDefault(size = 10,sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findAllByAccountNumber(pageable,accountNumber);
    }
    
    @GetMapping("/{accountNumber}/transactions/daterange")
    public Page<TransactionResponseDTO> findTransactionsByAccountAndDateRange(
            @PathVariable String accountNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault(size = 10, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findByAccountAndDateRange(accountNumber, startDate, endDate, pageable);
    }
    
    @GetMapping("/type/{transactionType}")
    public Page<TransactionResponseDTO> findByTransactionType(@PathVariable TransactionType transactionType, @PageableDefault(size = 10, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findByTransactionType(transactionType, pageable);
    }
    
    @GetMapping("/amount")
    public Page<TransactionResponseDTO> findByMinAmount(@RequestParam Double minAmount, @PageableDefault(size = 10, sort = "amount", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findByMinAmount(minAmount, pageable);
    }
    
    @GetMapping("/{accountNumber}/type/{transactionType}")
    public Page<TransactionResponseDTO> findByAccountAndTransactionType(@PathVariable String accountNumber, @PathVariable TransactionType transactionType, @PageableDefault(size = 10, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.findByAccountAndTransactionType(accountNumber, transactionType, pageable);
    }

}
