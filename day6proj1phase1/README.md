# day6proj1phase1

## Project Description
day6proj1phase1 is a simple console-based bug tracking application developed in Java. It allows users to insert new bugs with a title and description, list all existing bugs, and exit the application. The application uses a PostgreSQL database to persist bug data.

## Prerequisites
- Java 21
- Maven
- PostgreSQL database

## Build and Run Instructions

1. Ensure PostgreSQL is installed and running.
2. Create a database and a table named `Bugs` with the following schema:

```sql
CREATE TABLE Bugs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50)
);
```

3. Configure your database connection in the `DBUtil` class (not detailed here).
4. Build the project using Maven:

```bash
mvn clean install
```

5. Run the application using the exec plugin:

```bash
mvn exec:java
```

Alternatively, you can run the Main class directly from your IDE.

## Features
- Insert a new bug with title, description, and default status "Open".
- List all bugs stored in the database.
- Exit the application.

## Project Structure
```
day6proj1phase1/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── day6proj1phase1/
│   │   │           ├── Main.java
│   │   │           ├── controller/
│   │   │           │   └── BugController.java
│   │   │           ├── entity/
│   │   │           │   └── Bug.java
│   │   │           ├── repository/
│   │   │           │   ├── BugRepository.java
│   │   │           │   └── BugRepositoryImpl.java
│   │   │           ├── service/
│   │   │           │   └── BugService.java
│   │   │           └── util/
│   │   │               └── DBUtil.java
│   └── test/
│       └── java/
│           └── com/
│               └── day6proj1phase1/
│                   └── AppTest.java
└── target/
```

## Database
The application uses a PostgreSQL database to store bug information. The `Bugs` table contains the following columns:
- `id`: Auto-incremented primary key.
- `title`: Title of the bug.
- `description`: Detailed description of the bug.
- `status`: Current status of the bug (e.g., Open).

## Notes
- Ensure the database connection details in `DBUtil` are correctly configured before running the application.
- The application currently supports basic insert and list operations via a console menu.


