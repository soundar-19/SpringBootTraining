# IssueTrackerORM Project

## Overview
This project is a phase of an Issue Tracker application built using Spring Boot and JPA (Java Persistence API). The project is currently incomplete and under active development. The main focus so far has been on implementing the core bug tracking functionality, including managing bugs, their statuses, and associations with projects and users.

## What Has Been Done So Far
- Created the domain model for `Bug` with fields such as title, description, status, and relationships to `Project` and `User`.
- Implemented the `BugRepository` interface extending `JpaRepository` with custom queries to:
  - Find bugs by status
  - Find bugs by project ID
  - Find bugs assigned to a specific user
  - Find unresolved bugs assigned to a user
  - Count bugs by project ID
- Developed the `BugService` class to provide business logic and interact with the repository.
- Created the `BugController` REST API exposing endpoints to:
  - Retrieve all bugs
  - Retrieve bugs filtered by status, project, assignee, unresolved status, and count by project.

## Project Structure

```
IssueTrackerORM/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/IssueTrackerORM/
│   │   │       ├── controller/       # REST API controllers (e.g., BugController)
│   │   │       ├── domain/           # JPA entity classes (e.g., Bug, Project, User)
│   │   │       ├── repository/       # Spring Data JPA repositories (e.g., BugRepository)
│   │   │       ├── service/          # Service layer classes (e.g., BugService)
│   │   │       └── IssueTrackerOrmApplication.java  # Main Spring Boot application class
│   │   └── resources/
│   │       ├── application.properties  # Application configuration
│   │       ├── static/                  # Static resources (CSS, JS, images)
│   │       └── templates/               # Template files (e.g., Thymeleaf)
│   └── test/
│       └── java/com/example/IssueTrackerORM/  # Test classes
├── pom.xml                            # Maven project descriptor
├── mvnw, mvnw.cmd                    # Maven wrapper scripts
├── .gitignore, .gitattributes        # Git configuration files
└── README.md                         # This file
```

## Next Steps
- Implement additional domain models such as `Project` and `User` in more detail.
- Add create, update, and delete operations for bugs and other entities.
- Implement authentication and authorization.
- Develop frontend UI to interact with the backend APIs.
- Add more comprehensive tests.

---

This README will be updated as the project progresses.
