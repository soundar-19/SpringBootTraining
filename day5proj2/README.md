# 🧩 Mini Issue Tracker – Console Application (Day 5 – Project 2)

## 📄 Overview

This is a console-based mini bug tracker I developed using **Java, JDBC, and PostgreSQL**. It is a single-phase project aimed at strengthening my understanding of database interaction through JDBC and practicing CRUD operations in Java.

---

## 🎯 Objective

To build a simple and functional bug tracking application with the following features:

* Add new bugs with a title and description
* View all existing bugs
* Update the status of a bug using its ID
* Delete bugs by their ID
* Interact with the application through a menu-driven console interface

---

## 🔧 Technologies Used

* Java 21
* JDBC (Java Database Connectivity)
* PostgreSQL
* Maven

---

## 🔹 Project Structure

```
mini-issue-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bugtracker/
│   │   │       ├── dao/
│   │   │       │   └── BugDAO.java
│   │   │       ├── main/
│   │   │       │   └── Main.java
│   │   │       ├── model/
│   │   │       │   └── Bug.java
│   │   │       └── util/
│   │   │           └── DBUtil.java
│   └── resources/
├── pom.xml
└── README.md
```

---

## 🧾 Database Schema

I used the following PostgreSQL table structure for storing bug data:

```sql
CREATE TABLE bugs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    description TEXT,
    status VARCHAR(50)
);
```

---

## 💻 How to Run

1. Make sure PostgreSQL is installed and the server is running.
2. Create the `bugs` table using the schema provided above.
3. Update the database credentials in `DBUtil.java`.
4. Compile and run the project using Maven:

   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.bugtracker.main.Main"
   ```

---

## 📋 Console Menu Options

1. **Add Bug** – Enter bug title and description to add a new bug.
2. **View All Bugs** – Display all bug records from the database.
3. **Update Bug Status by ID** – Update a bug's status by entering its ID.
4. **Delete Bug by ID** – Delete a specific bug by providing its ID.
5. **Exit** – Exit the application.



## 👤 Author

- **Soundar Raja B**
