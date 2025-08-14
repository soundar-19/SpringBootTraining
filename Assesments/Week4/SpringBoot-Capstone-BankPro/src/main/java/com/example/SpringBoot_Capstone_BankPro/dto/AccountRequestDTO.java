package com.example.SpringBoot_Capstone_BankPro.dto;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    @NotBlank(message = "Account number is required")
    private String accountNumber;
    
    @NotNull(message = "Account type is required")
    private AccountType accountType;
    
    @NotNull(message = "Balance is required")
    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance;
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    public Account toEntity() {
        Account account = new Account();
        account.setAccountNumber(this.accountNumber);
        account.setAccountType(this.accountType);
        account.setBalance(this.balance);
        return account;
    }
}