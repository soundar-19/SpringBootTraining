package com.bankmanagementsystem.main;

import com.bankmanagementsystem.exceptions.InvalidTransactionException;
import com.bankmanagementsystem.models.Account;
import com.bankmanagementsystem.services.AccountService;
import com.bankmanagementsystem.services.TransactionService;
import com.bankmanagementsystem.ui.AccountCenterUI;
import com.bankmanagementsystem.ui.AccountDetailsUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BankManagementSystem {
    public static void main(String[] args){

        try(Scanner sc = new Scanner(System.in)){
        Map<Long, Account> accounts = new HashMap<>();
        AccountService accountService = new AccountService(accounts);
        System.out.println("Welcome to the Bank Management System");
        do{
            AccountCenterUI.show();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    accountService.createAccount();
                break;

                case 2:
                    System.out.print("Enter Account Number to delete: ");
                    long accountNumber = sc.nextLong();
                    sc.nextLine();
                    accountService.deleteAccount(accountNumber);
                break;

                case 3:
                    Account selectedAccount = accountService.selectAccount();
                    if(selectedAccount == null) continue;
                    TransactionService transactionService = selectedAccount.getTransactionService();
                    boolean canContinue = (selectedAccount != null);
                    while(canContinue) {
                        AccountDetailsUI.show();
                        int accountChoice = sc.nextInt();
                        sc.nextLine();
                        switch (accountChoice) {
                            case 1:
                                System.out.print("Enter amount to deposit: ");
                                double depositAmount = sc.nextDouble();
                                sc.nextLine();
                                transactionService.deposit(depositAmount);
                            break;

                            case 2:
                                System.out.print("Enter amount to withdraw: ");
                                double withdrawAmount = sc.nextDouble();
                                sc.nextLine();
                                try {
                                    transactionService.withdraw(withdrawAmount);
                                } catch (InvalidTransactionException e) {
                                    System.out.println(e.getMessage());
                                }
                            break;

                            case 3:
                                transactionService.balanceEnquiry();
                            break;

                            case 4:
                                accountService.displayAccountDetails(selectedAccount);
                            break;

                            case 5:
                                transactionService.printTransactionHistory();
                            break;

                            case 6:
                                canContinue = false;
                            break;

                            case 7:
                                System.out.println("Exiting the Bank Management System.");
                    System.exit(0);
                            break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    }
                
                break;

                case 4:
                    accountService.searchAccount();
                break;

                case 5:
                    accountService.displayAllAccounts();
                break;


                case 6:
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

