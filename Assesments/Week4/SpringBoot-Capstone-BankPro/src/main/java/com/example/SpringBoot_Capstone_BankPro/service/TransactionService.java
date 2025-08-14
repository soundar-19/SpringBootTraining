package com.example.SpringBoot_Capstone_BankPro.service;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionResponseDTO;

public interface TransactionService {
    TransactionResponseDTO findById(Long id);
    TransactionResponseDTO Deposit(TransactionRequestDTO transactionRequestDTO);
    TransactionResponseDTO Withdraw(TransactionRequestDTO transactionRequestDTO);
    void Transfer(String senderAC, String receiverAC, Double amount);
    Page<TransactionResponseDTO> findAll(Pageable pageable);
    Page<TransactionResponseDTO> findAllByAccountNumber(Pageable pageable, String accountNumber);
    Page<TransactionResponseDTO> findByAccountAndDateRange(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<TransactionResponseDTO> findByTransactionType(TransactionType transactionType, Pageable pageable);
    Page<TransactionResponseDTO> findByMinAmount(Double minAmount, Pageable pageable);
    Page<TransactionResponseDTO> findByAccountAndTransactionType(String accountNumber, TransactionType transactionType, Pageable pageable);
    void delete(Long id);
}
