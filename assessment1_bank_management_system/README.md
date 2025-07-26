# 🌐 Bank Management System

## 📄 Project Overview

This is a **console-based Bank Management System** developed using Java. The application enables users to manage bank accounts and perform fundamental banking operations through a menu-driven interface. Key functionalities include creating and managing accounts, executing deposits and withdrawals, checking balances, and viewing transaction history.
## 📁 Folder Structure

```
assessment1_bank_management_system/
│
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── bankmanagementsystem/
│   │   │   │   │   ├── App.java
│   │   │   │   │   ├── exceptions/
│   │   │   │   │   │   └── InvalidTransactionException.java
│   │   │   │   │   ├── interfaces/
│   │   │   │   │   │   └── TransactionServiceInterface.java
│   │   │   │   │   ├── main/
│   │   │   │   │   │   └── BankManagementSystem.java
│   │   │   │   │   ├── models/
│   │   │   │   │   │   ├── Account.java
│   │   │   │   │   │   └── User.java
│   │   │   │   │   ├── services/
│   │   │   │   │   │   ├── AccountService.java
│   │   │   │   │   │   └── TransactionService.java
│   │   │   │   │   └── ui/
│   │   │   │   │       ├── AccountCenterUI.java
│   │   │   │   │       └── AccountDetailsUI.java
│   └── test/
│       └── java/
│           └── com/
│               └── bankmanagementsystem/
│                   └── AppTest.java
├── target/
```


## ✨ Key Features

* Create new bank accounts with user details (name, mobile number, email, address, account type)
* Delete existing accounts using the account number
* Search for and view account details by account number
* Display a list of all accounts
* Perform account operations:

  * Deposit funds
  * Withdraw funds with validation
  * Balance enquiry
  * View account details
  * Display transaction history
* Exception handling for invalid operations and input errors
* Menu-driven UI for intuitive navigation

## 💻 Technologies Used

* Java SE (Standard Edition)
* Text-based (console) user interface

## 🚀 Getting Started

1. Ensure Java (JDK 8 or later) is installed.
2. Clone the project and navigate to the root folder.
3. Compile the source code located in the `src` directory.
4. Run the `BankManagementSystem` class located in `com.bankmanagementsystem.main`.
5. Interact with the application using the displayed menu options.

## 🔧 Usage Guide

* Upon running the program, you'll be presented with a main menu to create, search, delete, or display accounts.
* Select an account to manage transactions including deposit, withdrawal, balance check, or transaction history.
* Follow input prompts carefully. The system guides you through all steps.
* Exit the program using the menu option at any time.

## ⚠️ Exception Management

* Invalid user inputs are gracefully handled with user-friendly messages.
* Withdrawals are verified to ensure the requested amount is positive and within available balance.
* Custom exceptions, such as `InvalidTransactionException`, provide robust error handling.

## 👤 Author

Developed by **Soundar Raja B.** as part of a technical assessment project on banking application development.

---
