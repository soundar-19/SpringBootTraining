package com.bankmanagementsystem.services;

import com.bankmanagementsystem.models.Account;

public class AccountService {
    private Account account;
    public AccountService(Account account) {
        this.account = account;
    }
    public void displayAccountDetails(Account account){
        this.account = account;
    }
    public void displayAccountDetails(){
        System.out.println("\nAccount Details");
        System.out.println("Account Holder Name: " + account.getUserName());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Available Balance: " + account.getBalance());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Branch Name: " + account.getBranchName());
        System.out.println("IFSC Code: " + account.getIfscCode());
        System.out.println("Account Status: " + account.getStatus());
        System.out.println("Created Date: " + account.getCreatedDate());
        System.out.println();
        System.out.println("Account Holder Details: ");
        System.out.println("Name: " + account.getUserName());
        System.out.println("Email: " + account.getEmail());
        System.out.println("Mobile Number: " + account.getMobileNumber());
        System.out.println("Address: " + account.getAddress());
        System.out.println();
    }
}
