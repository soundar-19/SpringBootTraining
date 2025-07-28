# Bug Tracking System (day6proj2)

## Description
This is a console-based bug tracking application that allows users to insert, list, update, and delete bugs stored in a database. The application provides a menu-driven interface to manage bugs efficiently.

## Features
- Insert a new bug with title, description, and status.
- List all existing bugs.
- Update the status of a bug by its ID.
- Delete a bug by its ID.
- Exit the application.

## Project Structure

```
com.day6proj2
│
├── Main.java                 # Entry point of the application
│
├── controller
│   └── BugController.java   # Handles user interaction and menu
│
├── service
│   └── BugService.java      # Business logic for bug management
│
├── repository
│   ├── BugRepository.java        # Interface defining data operations
│   └── BugRepositoryImpl.java    # Implementation of data operations using JDBC
│
├── entity
│   └── Bug.java              # Bug entity/model class
│
└── util
    └── DBUtil.java           # Utility class for database connection
```

## How to Run
1. Ensure you have a database set up with a table named `bugs` having the following columns:
   - `id` (int, primary key, auto-increment)
   - `title` (varchar)
   - `description` (varchar)
   - `status` (varchar)

2. Configure your database connection in `DBUtil.java`.

3. Compile and run the `Main.java` class to start the application:
   ```
   java com.day6proj2.Main
   ```

4. Use the menu options to manage bugs.

## Dependencies
- Java Development Kit (JDK)
- JDBC driver for your database
- Database connection configured in `DBUtil.java`

## Author
- **Soundar Raja B**
