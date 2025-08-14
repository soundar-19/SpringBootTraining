# 🏦 BankPro - Banking Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](https://www.h2database.com/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> A comprehensive banking management system built with Spring Boot, featuring customer management, account operations, and transaction processing with advanced filtering capabilities.

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Security](#-security)
- [Testing with Postman](#-testing-with-postman)
- [Swagger UI](#-swagger-ui)
- [Contributing](#-contributing)
- [Author](#-author)

## 🎯 Overview

BankPro is a modern banking management system that provides comprehensive APIs for managing customers, accounts, and transactions. Built with Spring Boot and following best practices, it offers secure, scalable, and maintainable banking operations.

### Key Capabilities
- **Customer Management**: Create, update, and manage customer profiles
- **Account Operations**: Handle multiple account types (Savings/Current)
- **Transaction Processing**: Secure money transfers with transactional integrity
- **Advanced Filtering**: Search and filter data with multiple criteria
- **Security**: Role-based access control with Spring Security
- **Real-time Balance Updates**: Automatic balance calculations

## ✨ Features

### 🏛️ Core Banking Features
- ✅ Customer registration and profile management
- ✅ Multiple account types (Savings & Current)
- ✅ Secure money transfers between accounts
- ✅ Transaction history with pagination
- ✅ Real-time balance updates
- ✅ Insufficient balance validation

### 🔍 Advanced Filtering
- ✅ Search customers by name or email
- ✅ Filter accounts by type or minimum balance
- ✅ Filter transactions by type, amount, or date range
- ✅ Combined filters for complex queries

### 🛡️ Security & Validation
- ✅ Role-based access control (ADMIN/USER)
- ✅ Input validation with custom error messages
- ✅ Global exception handling
- ✅ Secure password encoding

### 📊 Additional Features
- ✅ Pagination and sorting for all endpoints
- ✅ Comprehensive API documentation
- ✅ Swagger UI integration
- ✅ H2 database with web console
- ✅ RESTful API design

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.5.4 | Application Framework |
| **Spring Security** | 6.x | Authentication & Authorization |
| **Spring Data JPA** | 3.x | Data Access Layer |
| **H2 Database** | 2.x | In-Memory Database |
| **Maven** | 3.6+ | Build Tool |
| **Lombok** | 1.18.34 | Code Generation |
| **Swagger/OpenAPI** | 2.7.0 | API Documentation |
| **Bean Validation** | 3.x | Input Validation |

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │────│    Service      │────│   Repository    │
│     Layer       │    │     Layer       │    │     Layer       │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │   Domain/       │    │   H2 Database   │
│   Validation    │    │   Entities      │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Layer Responsibilities
- **Controller Layer**: REST endpoints, request/response handling
- **Service Layer**: Business logic, transaction management
- **Repository Layer**: Data access, custom queries
- **Domain Layer**: JPA entities, relationships
- **DTO Layer**: Data transfer objects, validation

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- Git

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/soundar-19/SpringBootTraining.git
cd Assesments/week4/SpringBoot-Capstone-BankPro
```

2. **Build the project**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring-boot:run
```

4. **Access the application**
- **API Base URL**: `http://localhost:8080/api`
- **H2 Console**: `http://localhost:8080/h2-console`
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

### Default Credentials
| Role | Username | Password |
|------|----------|----------|
| Admin | `admin` | `admin123` |
| User | `user` | `user123` |

## 📚 API Documentation

### 👥 Customer Management APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `GET` | `/api/customers` | Get all customers (paginated) | ADMIN, USER |
| `GET` | `/api/customers/{id}` | Get customer by ID | ADMIN, USER |
| `GET` | `/api/customers/email/{email}` | Get customer by email | ADMIN, USER |
| `GET` | `/api/customers/search/name?name={name}` | Search customers by name | ADMIN, USER |
| `POST` | `/api/customers` | Create new customer | ADMIN |
| `PUT` | `/api/customers/{id}` | Update customer | ADMIN |
| `DELETE` | `/api/customers/{id}` | Delete customer | ADMIN |

### 🏦 Account Management APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `GET` | `/api/accounts` | Get all accounts (paginated) | ADMIN, USER |
| `GET` | `/api/accounts/{id}` | Get account by ID | ADMIN, USER |
| `GET` | `/api/accounts/account/{accountNumber}` | Get account by number | ADMIN, USER |
| `GET` | `/api/accounts/customer/{customerId}` | Get accounts by customer | ADMIN, USER |
| `GET` | `/api/accounts/type/{accountType}` | Filter by account type | ADMIN, USER |
| `GET` | `/api/accounts/balance?minBalance={amount}` | Filter by minimum balance | ADMIN, USER |
| `POST` | `/api/accounts` | Create new account | ADMIN |
| `PUT` | `/api/accounts/{id}` | Update account | ADMIN |
| `DELETE` | `/api/accounts/{id}` | Delete account | ADMIN |

### 💳 Transaction Management APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `GET` | `/api/transactions` | Get all transactions (paginated) | ADMIN, USER |
| `GET` | `/api/transactions/{id}` | Get transaction by ID | ADMIN, USER |
| `GET` | `/api/transactions/type/{transactionType}` | Filter by transaction type | ADMIN, USER |
| `GET` | `/api/transactions/amount?minAmount={amount}` | Filter by minimum amount | ADMIN, USER |
| `GET` | `/api/transactions/{accountNumber}/transactions` | Get account transactions | ADMIN, USER |
| `GET` | `/api/transactions/{accountNumber}/transactions/daterange` | Filter by date range | ADMIN, USER |
| `POST` | `/api/transactions/deposit` | Deposit money | ADMIN |
| `POST` | `/api/transactions/withdraw` | Withdraw money | ADMIN |
| `POST` | `/api/transactions/transfer` | Transfer money | ADMIN |
| `DELETE` | `/api/transactions/{id}` | Delete transaction | ADMIN |

### 📝 Request/Response Examples

#### Create Customer
```json
POST /api/customers
{
  "name": "John Doe",
  "email": "john.doe@email.com",
  "phone": "1234567890"
}
```

#### Create Account
```json
POST /api/accounts
{
  "accountNumber": "ACC001",
  "accountType": "SAVINGS",
  "balance": 1000.0,
  "customerId": 1
}
```

#### Transfer Money
```json
POST /api/transactions/transfer
{
  "senderAC": "ACC001",
  "receiverAC": "ACC002",
  "amount": 500.0
}
```

## 🗄️ Database Schema

### Entity Relationships
```
Customer (1) ──────── (*) Account (1) ──────── (*) Transaction
    │                      │                        │
    ├─ id (PK)             ├─ id (PK)               ├─ id (PK)
    ├─ name                ├─ accountNumber         ├─ transactionDate
    ├─ email               ├─ accountType           ├─ amount
    └─ phone               ├─ balance               ├─ transactionType
                           └─ customer_id (FK)      ├─ balanceAfterTransaction
                                                    └─ account_id (FK)
```

### H2 Database Configuration
```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:file:./data/bankpro
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🔐 Security

### Authentication
- **Type**: HTTP Basic Authentication
- **Roles**: ADMIN, USER
- **Password Encoding**: BCrypt

### Authorization Matrix
| Operation | ADMIN | USER |
|-----------|-------|------|
| View Data | ✅ | ✅ |
| Create | ✅ | ❌ |
| Update | ✅ | ❌ |
| Delete | ✅ | ❌ |
| Transactions | ✅ | ❌ |

### Security Headers
- CSRF Protection: Disabled for API
- CORS: Configured for development
- Frame Options: Disabled for H2 Console

## 🧪 Testing with Postman

### Import Collection
1. Download Postman collection
2. Import into Postman
3. Set environment variables:
   - `baseUrl`: `http://localhost:8080`
   - `adminAuth`: `admin:admin123` (Base64 encoded)
   - `userAuth`: `user:user123` (Base64 encoded)

### Key Test Scenarios

#### 1. Create Customer (Admin Only)
<img width="1738" height="579" alt="image" src="https://github.com/user-attachments/assets/9b3417cc-76b4-4f2d-ac8d-66a0c992a475" />


#### 2. Create Account (Admin Only)
<img width="1753" height="599" alt="image" src="https://github.com/user-attachments/assets/2a9ba02c-60f6-49fd-88da-8f91e14d198e" />


#### 3. Deposit Money (Admin Only)
<img width="1752" height="603" alt="image" src="https://github.com/user-attachments/assets/3e9e33f7-9ae0-468e-8f56-858c4a4a47c1" />


#### 4. Transfer Money (Admin Only)
<img width="1759" height="473" alt="image" src="https://github.com/user-attachments/assets/a6f023e5-2b31-4198-8d39-e9525a552a4a" />


#### 5. Get Transaction History (Admin/User)
<img width="1747" height="622" alt="image" src="https://github.com/user-attachments/assets/1449f0cf-fe52-4ad3-97a2-88e6f5281306" />


#### 6. Filter Accounts by Type (Admin/User)
<img width="1749" height="821" alt="image" src="https://github.com/user-attachments/assets/a5c8aece-0a70-4ffa-a4b4-24669ee452bf" />

### Authentication Setup
Add to request headers:
```
Authorization: Basic YWRtaW46YWRtaW4xMjM=
Content-Type: application/json
```

## 📖 Swagger UI

Access comprehensive API documentation at: `http://localhost:8080/swagger-ui/index.html`

### Features
- **Interactive API Testing**: Test endpoints directly from browser
- **Request/Response Examples**: See sample data for all endpoints
- **Authentication**: Built-in authentication for secured endpoints
- **Schema Documentation**: Detailed DTO and entity schemas

### Swagger Screenshot
<img width="1838" height="715" alt="image" src="https://github.com/user-attachments/assets/08ad73d3-f71d-45a1-bf14-35b0c8347f51" />
<img width="1820" height="621" alt="image" src="https://github.com/user-attachments/assets/912fd962-19cc-4e6c-88f2-0456c2736d93" />
<img width="1821" height="696" alt="image" src="https://github.com/user-attachments/assets/8b17e516-9c7a-4dae-829a-bb5bb8607892" />
<img width="1817" height="827" alt="image" src="https://github.com/user-attachments/assets/cd59bce1-3151-4d4a-a29c-6f21fa316245" />
<img width="1203" height="874" alt="image" src="https://github.com/user-attachments/assets/5ecfd72c-3006-4d41-99ea-7f1a1564d03c" />

---

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

### Development Guidelines
- Follow Spring Boot best practices
- Write unit tests for new features
- Update documentation for API changes
- Use conventional commit messages

## 👨‍💻 Author

**Soundar Raja B**


## 🙏 Acknowledgments

- Spring Boot Team for the excellent framework
- H2 Database for the lightweight database solution
- Swagger/OpenAPI for API documentation
- Lombok for reducing boilerplate code

---

<div align="center">
  <p>⭐ Star this repository if you found it helpful!</p>
</div>
