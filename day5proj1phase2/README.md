# 🔧 Bug Tracker Application - day5proj1phase2

## 📄 Overview

A console-based Java application for managing software bugs using a **PostgreSQL** database. It provides functionalities to insert, view, delete, and update bug entries. JDBC is used for database interaction, ensuring persistent storage.

---

## 🔺 Features

* Add new bugs with a title, description, and default status "Open"
* List all recorded bugs with full details
* Delete bugs by ID
* Update the status of a specific bug by ID
* User-friendly console menu for interaction

---

## 🛠️ Technologies Used

* Java 21
* JDBC (Java Database Connectivity)
* PostgreSQL

---

## 🔹 Project Structure

```
day5proj1phase2/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/day5proj1phase2/
│   │   │       ├── dao/
│   │   │       │   └── BugDAO.java
│   │   │       ├── main/
│   │   │       │   └── Main.java
│   │   │       ├── models/
│   │   │       │   └── Bug.java
│   │   │       └── util/
│   │   │           └── DBUtil.java
│   ├── test/
│   │   └── java/com/day5proj1phase2/
│   └── resources/
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🧾 Database Schema

Make sure your **PostgreSQL** database includes a `bugs` table with the following structure:

* `id` (SERIAL PRIMARY KEY)
* `title` (VARCHAR)
* `description` (VARCHAR)
* `status` (VARCHAR)

---

## 💻 How to Run

1. Ensure your PostgreSQL database is running and accessible.
2. Configure the DB credentials in `DBUtil.java`.
3. Compile the project using Maven:

   ```bash
   mvn compile
   ```
4. Run the main class:

   ```bash
   mvn exec:java -Dexec.mainClass="com.day5proj1phase2.main.Main"
   ```
5. Use the menu to interact with the bug tracker.

---

## 🌐 Console Menu Options

1. **Insert a Bug**: Add a new bug entry with a title and description (status defaults to "Open").
2. **View All Bugs**: Display all bugs currently in the database.
3. **Delete a Bug by ID**: Remove a bug using its numeric ID.
4. **Update Bug Status by ID**: Change a bug's status by specifying its ID and new status.
5. **Exit**: Close the application.

---

## ⚠️ Notes

* All database credentials and connection logic are managed in `DBUtil.java`.
* Proper exception handling is implemented to manage SQL errors gracefully.

---

## 👤 Author
- **Soundar Raja B**
