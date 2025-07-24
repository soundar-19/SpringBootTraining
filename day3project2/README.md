# 🐞 Issue Tracker Application

## 📋 Project Description

This project is a simple **Issue Tracker Application** built using Java and Object-Oriented Programming principles. It models two main types of issues: **Bugs** and **Tasks**, both extending an abstract `Issue` class. Each issue type implements the `Reportable` interface, allowing for polymorphic report generation.

The application demonstrates:
- Use of abstract classes and interfaces
- Inheritance and method overriding
- Polymorphism via the `Reportable` interface
- Clean package structure (`models`, `interfaces`, main application)

The main application creates a list of issues, displays their details using the overridden `display()` method, and generates detailed reports for each issue using the `generateReport()` method polymorphically.

---

## 🗂️ Project Structure

```
day3project2/
├── .gitignore
├── pom.xml
├── day3project2 Notes.md
├── src/
│   └── main/
│       └── java/
│           ├── IssueTrackerApplication.java
│           ├── models/
│           │   ├── Bug.java
│           │   ├── Issue.java
│           │   └── Task.java
│           └── interfaces/
│               └── Reportable.java
│           └── com/
│               └── day3project2/
│                   └── App.java
│   └── test/
│       └── java/
│           └── com/
│               └── day3project2/
│                   └── AppTest.java
```

---

## 🚀 How to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```
2. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="IssueTrackerApplication"
   ```
   Or run `IssueTrackerApplication.java` directly from your IDE.

---

## 🧩 Key Classes

- **models.Issue**  
  Abstract base class for all issues. Contains common fields (`id`, `title`, `description`, `status`) and an abstract `display()` method.

- **models.Bug**  
  Extends `Issue` and implements `Reportable`. Adds a `severity` field. Overrides `display()` and `generateReport()` for bug-specific output.

- **models.Task**  
  Extends `Issue` and implements `Reportable`. Adds `assignedTo` and `due` fields. Overrides `display()` and `generateReport()` for task-specific output.

- **interfaces.Reportable**  
  Interface with a single method `generateReport()` for polymorphic reporting.

- **IssueTrackerApplication**  
  Main class. Demonstrates creation of issues, displaying them, and generating reports using polymorphism.

---

## 📝 Example Output

```
=== Issue List ===
ID: 1 | NullPointer Exception - Occurs on login | Status: Open | Severity: High
ID: 2 | UI Misalignment - Button not centered | Status: Open | Severity: Low
ID: 3 | Implement Feature X - Add new login method | Status: Open | Assigned To: Alice | Due in: 5 days
ID: 4 | Update Documentation - Revise API docs | Status: Open | Assigned To: Bob | Due in: 2 days

=== Reports ===
========== Bug Report ==========
Bug ID      : 1
Title       : NullPointer Exception
Description : Occurs on login
Status      : Open
Severity    : High
=========================================
========== Bug Report ==========
Bug ID      : 2
Title       : UI Misalignment
Description : Button not centered
Status      : Open
Severity    : Low
=========================================
----- Task Report -----
Task ID      : 3
Title        : Implement Feature X
Description  : Add new login method
Status       : Open
Assigned To  : Alice
Due In       : 5 days
--------------------------------
----- Task Report -----
Task ID      : 4
Title        : Update Documentation
Description  : Revise API docs
Status       : Open
Assigned To  : Bob
Due In       : 2 days
--------------------------------
```

---

## 👤 Author

- **Soundar Raja B**
- GitHub: [soundar-19](https://github.com/soundar-19)

---

> This project is part of a hands-on Java learning series, focusing on clean code, modular design, and real-world backend