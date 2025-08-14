package com.example.SpringBoot_Capstone_BankPro.dto;

import com.example.SpringBoot_Capstone_BankPro.domain.Transaction;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    
    @NotNull(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    public Transaction toEntity() {
        Transaction transaction = new Transaction();
        transaction.setAmount(this.amount);
        return transaction;
    }
}