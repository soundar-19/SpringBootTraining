# 📊 Employee Tax Reporting System – Day 3 Project 1 (Phase 2)

## 📝 Overview

This project demonstrates advanced **Java OOP** concepts by building an Employee Tax Reporting System. It extends the previous phase by introducing utility classes for reporting, department-wise analysis, and enhanced data handling.

---

## 🚀 Features

- **Employee Hierarchy:**  
  - `Employee` (abstract class)  
  - `FullTimeEmployee` and `PartTimeEmployee` (inherit Employee, implement tax calculation)
- **Tax Calculation:**  
  - Each employee type calculates tax based on salary and type.
- **Utility Class:**  
  - `TaxReportGenerator` in the `utils` package provides:
    - Display of all employee details
    - Sorting employees by tax amount
    - Total tax calculation
    - Department-wise employee listing and tax summary

---

## 📂 Project Structure

```
day3proj1phase2/
├── src/
│   └── main/
│       └── java/
│           ├── models/
│           │   ├── Employee.java
│           │   ├── FullTimeEmployee.java
│           │   └── PartTimeEmployee.java
│           └── utils/
│               └── TaxReportGenerator.java
├── pom.xml
└── README.md
```

---

## ⚙️ How to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```
2. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```
3. **Run tests:**
   ```bash
   mvn test
   ```

---

## 🎯 Learning Outcomes

- Practice advanced OOP: inheritance, abstraction, interfaces, encapsulation
- Implement utility/helper classes for reporting
- Organize code using packages (`models`, `utils`)
- Perform department-wise and tax-based analysis

---

## 👤 Author

- **Soundar Raja B**
- GitHub: [soundar-19](https://github.com/soundar-19)

---

> This project is part of a hands-on Java learning series, focusing on clean code, modular design, and real-