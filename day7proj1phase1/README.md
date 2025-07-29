# Leave Management Lite - day7proj1phase1

## Project Overview
Leave Management Lite is a simple Java-based leave management system designed to handle employee leave requests efficiently. This project is built using Java 21 and managed with Maven. It interacts with a PostgreSQL database for persistence.

## Project Structure

```
day7proj1phase1/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── leavemanagementlite/
│   │   │           ├── LeaveManagementLite.java
│   │   │           ├── controller/
│   │   │           │   └── LeaveController.java
│   │   │           ├── dao/
│   │   │           │   ├── EmployeeDAO.java
│   │   │           │   ├── LeaveRequestDAO.java
│   │   │           │   └── impl/
│   │   │           │       ├── EmployeeDAOImpl.java
│   │   │           │       └── LeaveRequestDAOImpl.java
│   │   │           ├── entity/
│   │   │           │   ├── Employee.java
│   │   │           │   └── LeaveRequest.java
│   │   │           ├── exceptions/
│   │   │           │   ├── EmployeeNotFoundException.java
│   │   │           │   ├── InvalidLeaveDateException.java
│   │   │           │   └── LeaveRequestNotFoundException.java
│   │   │           ├── service/
│   │   │           │   ├── EmployeeService.java
│   │   │           │   └── LeaveService.java
│   │   │           └── util/
│   │   │               └── DBUtil.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── leavemanagementlite/
│                   └── (test classes)
└── .gitignore
```

## Package Description

- **controller**: Contains REST controllers handling HTTP requests (e.g., `LeaveController`).
- **dao**: Data Access Objects interfaces and implementations for database operations.
- **entity**: Java classes representing database entities like `Employee` and `LeaveRequest`.
- **exceptions**: Custom exceptions for handling specific error cases.
- **service**: Business logic services managing leave and employee operations.
- **util**: Utility classes such as database connection helpers.
- **LeaveManagementLite.java**: Main application entry point.

## Prerequisites

- Java 21
- Maven 3.x
- PostgreSQL database

## Build and Run

1. Build the project using Maven:

```bash
mvn clean install
```

2. Run the application using the exec plugin:

```bash
mvn exec:java
```

The main class is `com.leavemanagementlite.LeaveManagementLite`.

## Dependencies

- JUnit 4.11 (for testing)
- PostgreSQL JDBC Driver 42.7.7

## Notes

- Update the database connection settings in `DBUtil.java` as per your environment.
- Ensure PostgreSQL is running and accessible before starting the application.
