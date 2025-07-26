package com.bankmanagementsystem.services;

import java.util.Map;
import java.util.Scanner;

import com.bankmanagementsystem.models.Account;

public class AccountService {
    private Map<Long, Account> accounts;
    private Scanner sc;
    public AccountService(Map<Long, Account> accounts) {
        this.accounts = accounts;
        this.sc = new Scanner(System.in);
    }
    public void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter Mobile Number: ");
        long mobileNumber = 0;
        while (mobileNumber == 0) {
            try {
                mobileNumber = Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid mobile number.");
            }
        }
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Account Type (savings/current): ");
        String accountType = sc.nextLine();
        // System.out.print("Enter Branch Name: ");
        // String branchName = sc.nextLine();
        // System.out.print("Enter IFSC Code: ");
        // String ifscCode = sc.nextLine();

        Account account = new Account(userName, mobileNumber, email, address, accountType);
        accounts.put(account.getAccountNumber(),account);
        System.out.println("Account [A/C : "+account.getAccountNumber()+"] created successfully!");
        
    }

    public void deleteAccount(long accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account [" + accountNumber + "] deleted successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        System.out.println("Available Accounts:");
        int index = 1;
        for (Account account : accounts.values()) {
            System.out.println(index + ". Account Number: " + account.getAccountNumber() + ", Account Holder: " + account.getUserName() + ", Balance: " + account.getBalance());
            index++;
        }
    }

    public Account selectAccount() {
        displayAllAccounts();
        if(accounts.isEmpty()) return null;
        System.out.print("Enter Account Number to select: ");
        long accountNumber = 0;
        while (accountNumber == 0) {
            try {
                accountNumber = Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid account number.");
            }
        }
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            System.out.println("Account not found.");
            return null;
        }
    }
    public void searchAccount(){
        System.out.print("Enter Account Number to search: ");
        long accountNumber = 0;
        while (accountNumber == 0) {
            try {
                accountNumber = Long.parseLong(sc.nextLine());
                if (accounts.containsKey(accountNumber)) { 
                    Account account = accounts.get(accountNumber);
                    displayAccountDetails(account);
                } else {
                    System.out.println("Account not found.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid account number.");
            }
        }
    }
    
    public void displayAccountDetails(Account account){
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
