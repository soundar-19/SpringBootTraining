# IssueTrackerORM Project

## Overview
A complete Issue Tracker application built using Spring Boot and JPA (Java Persistence API) with PostgreSQL database. The application provides comprehensive bug tracking functionality with full CRUD operations for managing bugs, projects, and users.

## Features
### Domain Models
- **Bug**: Title, description, status, priority, createdDate, project association, user assignment
- **Project**: Name with bug relationships
- **User**: Name, role with bug assignments

### Bug Management
- Create, read bugs with auto-generated timestamps
- Filter by status, priority, project, assignee
- Find unresolved bugs by user
- Count bugs by project
- Priority-based sorting by creation date

### Project Management
- CRUD operations for projects
- Find projects by name
- Count total projects
- Find projects with assigned bugs

### User Management
- CRUD operations for users
- Filter by name and role
- Count users by role
- Find users with assigned bugs

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

## API Endpoints

### Bug APIs (`/api/bugs`)
- `GET /` - Get all bugs
- `GET /{id}` - Get bug by ID
- `POST /create` - Create new bug
- `GET /status/{status}` - Get bugs by status
- `GET /priority/{priority}` - Get bugs by priority (sorted by date)
- `GET /project/{projectId}` - Get bugs by project
- `GET /assignedTo/{userId}` - Get bugs by assignee
- `GET /unresolved/{userId}` - Get unresolved bugs by user
- `GET /count/{projectId}` - Count bugs by project

### Project APIs (`/api/projects`)
- `GET /` - Get all projects
- `GET /id/{projectId}` - Get project by ID
- `GET /name/{name}` - Get project by name
- `POST /create` - Create new project
- `GET /count` - Count all projects
- `GET /with-bugs` - Get projects with bugs

### User APIs (`/api/users`)
- `GET /` - Get all users
- `GET /{id}` - Get user by ID
- `GET /name/{name}` - Get user by name
- `GET /role/{role}` - Get users by role
- `POST /create` - Create new user
- `GET /count/role/{role}` - Count users by role
- `GET /with-bugs` - Get users with assigned bugs

## Technology Stack
- **Framework**: Spring Boot 3.5.4
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Java Version**: 21
- **Build Tool**: Maven

## Getting Started
1. Configure PostgreSQL database connection in `application.properties`
2. Run `mvn spring-boot:run`
3. Access APIs at `http://localhost:8080/api/`
