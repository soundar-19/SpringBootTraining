package com.bankmanagementsystem.interfaces;

import com.bankmanagementsystem.exceptions.InvalidTransactionException;

public interface TransactionServiceInterface {
    public void deposit(double amount);
    public void withdraw(double amount) throws InvalidTransactionException;
    public void balanceEnquiry();
}
