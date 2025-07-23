# 👔 Employee Tax Calculation System – Day 3 Project 1 (Phase 1)

## 🔹 Project Name: `day3proj1phase1`

## 🌟 Objective

This project demonstrates **Object-Oriented Programming (OOP)** concepts in Java by modeling an employee payroll and tax calculation system. It features inheritance, abstraction, interfaces, and polymorphism.

---

## ✅ Features Implemented

### 1. Employee Hierarchy

- **`Employee` (abstract class):**
  - Field: `name`
  - Abstract method: `calculateSalary()`
- **`FullTimeEmployee` (extends Employee, implements Taxable):**
  - Field: `salary`
  - Implements `calculateSalary()` and `calculateTax()` (20% tax)
- **`PartTimeEmployee` (extends Employee, implements Taxable):**
  - Fields: `hourlyRate`, `duration`
  - Implements `calculateSalary()` and `calculateTax()` (10% tax)

### 2. Taxable Interface

- **`Taxable` (interface):**
  - Method: `calculateTax()`

### 3. Main Application

- Creates multiple full-time and part-time employees.
- Stores them in a list.
- Calculates and displays tax for each employee using polymorphism.

---

## ⚙️ Technical Stack

- **Language:** Java
- **Java Version:** 17+
- **Build Tool:** Maven
- **Testing:** JUnit

---

## 📂 Project Structure

```
day3proj1phase1/
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── main/
    │   │   │   └── Main.java
    │   │   ├── models/
    │   │   │   ├── Employee.java
    │   │   │   ├── FullTimeEmployee.java
    │   │   │   └── PartTimeEmployee.java
    │   │   └── interfaces/
    │   │       └── Taxable.java
    └── test/
        └── java/
            └── com/day3proj1phase1/AppTest.java
```

---

## 🏃 How to Run

1. Build the project:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```
3. Run tests:
   ```bash
   mvn test
   ```

---

## 🖊️ Summary

This project demonstrates:

- OOP principles: inheritance, abstraction, interfaces, and polymorphism
- Real-world payroll and tax calculation logic
- Clean separation of concerns

---

## 🚀Author
- **Soundar Raja B**
