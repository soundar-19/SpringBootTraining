# 🚀 Day5Proj1 - JDBC Connection Project

## 📄 Project Overview

**Day5Proj1** is a Java-based application designed to demonstrate the use of **JDBC (Java Database Connectivity)** for interacting with relational databases. It features DAO (Data Access Object) classes for handling database operations, model classes representing data entities, and utility components to assist with core functionality. The main execution entry point is located in `Main.java`.

## 📁 Project Structure

```
day5proj1/
│
├── pom.xml                      # Maven build file for dependencies and configuration
├── .gitignore                   # Git configuration to ignore files
├── README.md                    # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── jdbcconnection/
│   │   │           ├── dao/                # DAO classes for DB interaction
│   │   │           ├── main/               # Core logic/controller classes
│   │   │           ├── models/             # Data model/entity classes
│   │   │           └── util/               # Utility classes
│   └── test/                                
├── target/                                  
```

## 🚧 Build and Run Instructions

This is a **Maven-based project**. Ensure Maven is installed and configured properly.

### ✅ To build the project:

```bash
mvn clean install
```

### ▶️ To run the application:

Use your IDE or run the compiled `App` class manually.

## 📊 Features

* Demonstrates basic **JDBC operations** (connect, insert, update, delete, retrieve)
* Organized using the **DAO design pattern** for clean separation of logic
* Uses **POJO model classes** to represent database entities
* Includes utility classes to support reusability and modularity

## ⚙️ Setup Requirements

* Java JDK 17 or later
* Maven 3.x or later
* PostgreSQL
* Database connection details (URL, username, password) must be updated in the relevant configuration file or class

## 👤 Author

Created by **Soundar Raja B.** as part of a hands-on project demonstrating JDBC integration in Java.

---

