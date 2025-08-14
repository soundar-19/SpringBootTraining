package com.example.SpringBoot_Capstone_BankPro.dto;

import java.time.LocalDateTime;

import com.example.SpringBoot_Capstone_BankPro.domain.Transaction;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private String accountNumber;
    private LocalDateTime transactionDate;
    private double amount;
    private TransactionType transactionType;
    private double balanceAfterTransaction;
    public static TransactionResponseDTO toDTO(Transaction transaction) {
        return new TransactionResponseDTO(transaction.getId(), 
                                        transaction.getAccount() != null ? transaction.getAccount().getAccountNumber() : null,
                                        transaction.getTransactionDate(),
                                        transaction.getAmount(),
                                        transaction.getTransactionType(),
                                        transaction.getBalanceAfterTransaction()
                                        );
    }
}
