package com.bankmanagementsystem.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import com.bankmanagementsystem.exceptions.InvalidTransactionException;
import com.bankmanagementsystem.interfaces.TransactionServiceInterface;
import com.bankmanagementsystem.models.Account;

public class TransactionService implements TransactionServiceInterface {
    private Account account;
    private Stack<String> transactionHistory = new Stack<>();
    public TransactionService(Account account){
        this.account = account;
    }
    @Override
    public void deposit(double amount){
        double currentBalance = account.getBalance();
        currentBalance += amount;
        account.setBalance(currentBalance);
        String transaction = amount+" deposited to the account "+account.getAccountNumber()+" on "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+" successfully. Available balance : "+account.getBalance();
        transactionHistory.add(transaction);
        System.out.println(transaction);
    }
    @Override
    public void withdraw(double amount) throws InvalidTransactionException {
        double currentBalance = account.getBalance();
        if(amount > currentBalance || amount <= 0) throw new InvalidTransactionException("The withdraw amount should be less than or equal to the available balance and greater than zero.");
        currentBalance -= amount;
        account.setBalance(currentBalance);
        String transaction = amount+" withdrawn from the account "+account.getAccountNumber()+" on "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+" successfully. Available balance : "+account.getBalance();
        transactionHistory.add(transaction);
        System.out.println(transaction);
    }
    @Override
    public void balanceEnquiry(){
        System.out.println("Available balance as of "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+" is "+account.getBalance());
    }
    public void printTransactionHistory(){
        System.out.println("\nTransaction History for Account Number: " + account.getAccountNumber());
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for(int i=transactionHistory.size()-1; i>=0; i--){
                System.out.println(transactionHistory.get(i));
            }
        }
    }
}
