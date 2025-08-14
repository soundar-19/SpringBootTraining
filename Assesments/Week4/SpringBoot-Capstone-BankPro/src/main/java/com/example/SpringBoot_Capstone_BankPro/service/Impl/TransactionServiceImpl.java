package com.example.SpringBoot_Capstone_BankPro.service.Impl;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionRequestDTO;
import com.example.SpringBoot_Capstone_BankPro.dto.TransactionResponseDTO;
import com.example.SpringBoot_Capstone_BankPro.exception.InvalidTransactionException;
import com.example.SpringBoot_Capstone_BankPro.exception.ResourceNotFoundException;
import com.example.SpringBoot_Capstone_BankPro.repository.AccountRepository;
import com.example.SpringBoot_Capstone_BankPro.repository.TransactionRepository;
import com.example.SpringBoot_Capstone_BankPro.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionResponseDTO findById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null) throw new ResourceNotFoundException("There is no transaction with id: " + id);
        return TransactionResponseDTO.toDTO(transaction);
    }


    @Override
    public Page<TransactionResponseDTO> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable).map(TransactionResponseDTO::toDTO);
    }

    @Override
    public void delete(Long id) {
         transactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction with id: " + id + " does not exist."));
        transactionRepository.deleteById(id);
    }

    @Override
    public TransactionResponseDTO Deposit(TransactionRequestDTO transactionRequestDTO) {
        return createTransaction(transactionRequestDTO,TransactionType.CREDIT, 1);
}
    @Override
    public TransactionResponseDTO Withdraw(TransactionRequestDTO transactionRequestDTO) {
        return createTransaction(transactionRequestDTO,TransactionType.DEBIT, -1);
    }

    @Override
    @Transactional
    public void Transfer(String senderAC, String receiverAC, Double amount) {
        createTransaction(new TransactionRequestDTO(senderAC,amount),TransactionType.DEBIT, -1);
        createTransaction(new TransactionRequestDTO(receiverAC,amount), TransactionType.CREDIT,1);
    }

    @Override
    public Page<TransactionResponseDTO> findAllByAccountNumber(Pageable pageable, String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account with number: " + accountNumber + " does not exist."));
            
        return transactionRepository.findAllByAccount(pageable, account).map(TransactionResponseDTO::toDTO);
    }

    @Override
    public Page<TransactionResponseDTO> findByAccountAndDateRange(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account with number: " + accountNumber + " does not exist."));
            
        return transactionRepository.findByAccountAndDateRange(account, startDate, endDate, pageable)
            .map(TransactionResponseDTO::toDTO);
    }

    @Override
    public Page<TransactionResponseDTO> findByTransactionType(TransactionType transactionType, Pageable pageable) {
        return transactionRepository.findByTransactionType(transactionType, pageable)
            .map(TransactionResponseDTO::toDTO);
    }

    @Override
    public Page<TransactionResponseDTO> findByMinAmount(Double minAmount, Pageable pageable) {
        return transactionRepository.findByAmountGreaterThan(minAmount, pageable)
            .map(TransactionResponseDTO::toDTO);
    }

    @Override
    public Page<TransactionResponseDTO> findByAccountAndTransactionType(String accountNumber, TransactionType transactionType, Pageable pageable) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account with number: " + accountNumber + " does not exist."));
        return transactionRepository.findByAccountAndTransactionType(account, transactionType, pageable)
            .map(TransactionResponseDTO::toDTO);
    }

    private TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO,
          TransactionType transactionType,  int multiplier) {
        String accountNumber = transactionRequestDTO.getAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Account with number: " + accountNumber + " does not exist."));
        
        Transaction transaction = transactionRequestDTO.toEntity();
        transaction.setAccount(account);
        transaction.setTransactionType(transactionType);
        
        double newBalance = account.getBalance() + multiplier * transaction.getAmount();
        if(newBalance < 0) throw new InvalidTransactionException("Insufficient Balance");
        
        account.setBalance(newBalance);
        transaction.setBalanceAfterTransaction(newBalance);
        accountRepository.save(account);
        
        return TransactionResponseDTO.toDTO(transactionRepository.save(transaction));
    }

    
    
}
