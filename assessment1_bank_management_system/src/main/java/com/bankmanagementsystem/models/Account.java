package com.bankmanagementsystem.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account extends User{
    private long accountNumber;
    private String accountType;
    private double balance;
    private String branchName;
    private String ifscCode;
    private String status;
    private String createdDate;
    private User accountHolder;
    public static long acNumber = 1111;
    public Account(String userName, long mobileNumber, String email, String address, String accountType, 
                   String branchName, String ifscCode) {
        super(userName, mobileNumber, email, address);
        this.accountNumber = acNumber;
        acNumber++;
        this.accountType = accountType;
        this.balance = 0;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.status = "Open";
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    
}
