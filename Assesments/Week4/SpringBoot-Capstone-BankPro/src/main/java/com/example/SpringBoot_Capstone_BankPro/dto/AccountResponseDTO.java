package com.example.SpringBoot_Capstone_BankPro.dto;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private Long customerId;

    public static AccountResponseDTO toDTO(Account account) {
        return new AccountResponseDTO(account.getId(), account.getAccountNumber(),
                                    account.getAccountType(), account.getBalance(),
                                    account.getCustomer() != null ? account.getCustomer().getId() : null);
    }
}