package com.bankmanagementsystem.main;

import com.bankmanagementsystem.exceptions.InvalidTransactionException;
import com.bankmanagementsystem.models.Account;
import com.bankmanagementsystem.services.AccountService;
import com.bankmanagementsystem.services.TransactionService;

import java.util.Scanner;
public class BankManagementSystem {
    public static void main(String[] args){

        try(Scanner sc = new Scanner(System.in)){

        Account account = null;
        System.out.println("Welcome to the Bank Management System");
        do{
            System.out.println("\nPlease select an option: ");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Display Account Details");
            System.out.println("6. Print Transaction History");
            System.out.println("7. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    
                            if(account == null) {
                            System.out.print("Enter Account Holder Name: ");
                            String userName = sc.nextLine();
                            System.out.print("Enter Mobile Number: ");
                            long mobileNumber = 0;
                            while(mobileNumber==0){
                                try{
                                    mobileNumber = sc.nextLong();
                                }catch(Exception e){
                                    System.out.println("Invalid input. Please enter a valid mobile number.");
                                    sc.nextLine();
                                }
                            }
                            sc.nextLine(); 
                            System.out.print("Enter Email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter Address: ");
                            String address = sc.nextLine();
                            System.out.print("Enter Account Type (savings/current): ");
                            String accountType = sc.nextLine();
                            System.out.print("Enter Branch Name: ");
                            String branchName = sc.nextLine();
                            System.out.print("Enter IFSC Code: ");
                            String ifscCode = sc.nextLine();
                            System.out.println("Creating account...\n");
                            account = new Account(userName, mobileNumber, email, address, accountType, branchName, ifscCode);
                            System.out.println("Account created successfully!\n");
                        } else {
                            System.out.println("Account already exists.");
                        }
                    
                break;

                case 2:
                    if(account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        TransactionService transactionService = new TransactionService(account);
                        transactionService.deposit(amount);
                    } else {
                        System.out.println("Please create an account first.");
                    }
                break;

                case 3:
                    if(account != null) {
                        System.out.println("Available balance: " + account.getBalance());
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble();
                        TransactionService transactionService = new TransactionService(account);
                        try {
                            transactionService.withdraw(amount);
                        } catch (InvalidTransactionException e) {
                            System.out.println(e.getMessage());
                        }catch (Exception e) {
                            System.out.println("Error during withdrawal: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Please create an account first.");
                    }
                break;

                case 4:
                    if(account != null) {
                        TransactionService transactionService = new TransactionService(account);
                        transactionService.balanceEnquiry();
                    } else {
                        System.out.println("Please create an account first.");
                    }
                break;

                case 5:
                    if(account != null) {
                        AccountService accountService = new AccountService(account);
                        accountService.displayAccountDetails();
                    } else {
                        System.out.println("Please create an account first.");
                    }
                break;

                case 6:
                    if(account != null) {
                        TransactionService transactionService = new TransactionService(account);
                        transactionService.printTransactionHistory();
                    } else {
                        System.out.println("Please create an account first.");
                    }
                break;

                case 7:
                    System.out.println("Exiting the Bank Management System.");
                    System.exit(0);
                break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (true);
    }

    catch(Exception e) {
            System.out.println("An error occurred: Invalid Input" );
        }
    }
}

