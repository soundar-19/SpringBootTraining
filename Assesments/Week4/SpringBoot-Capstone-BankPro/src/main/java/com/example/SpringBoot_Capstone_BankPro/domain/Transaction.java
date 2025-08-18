package com.example.SpringBoot_Capstone_BankPro.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime transactionDate = LocalDateTime.now();
    private TransactionType transactionType;
    private double amount;
    
    @Column(name = "balance_after_transaction")
    private Double balanceAfterTransaction;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public enum TransactionType{
        CREDIT,DEBIT
    }
}
