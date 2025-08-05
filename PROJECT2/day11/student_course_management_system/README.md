# 🎓 Student Course Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)](https://maven.apache.org/)

A comprehensive **Spring Boot REST API** for managing students, courses, and enrollments with advanced business logic validation and PostgreSQL persistence.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Database Schema](#-database-schema)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Business Rules](#-business-rules)
- [Testing](#-testing)
- [Contributing](#-contributing)

## ✨ Features

### Core Functionality
- 🎯 **Student Management**: Complete CRUD operations with roll number validation
- 📚 **Course Management**: Full course lifecycle with credit validation (1-5)
- 📝 **Enrollment Management**: Advanced enrollment/unenrollment with duplicate prevention
- 🔍 **Advanced Queries**: Search by roll number, course code, title, and credits
- 🛡️ **Data Integrity**: Referential integrity and business rule enforcement

### Technical Features
- 🚀 **RESTful API**: Clean REST endpoints with proper HTTP status codes
- 🗄️ **PostgreSQL Integration**: Robust database persistence
- 🔄 **Bidirectional Relationships**: JPA entity relationships with lazy loading
- ✅ **Input Validation**: Comprehensive business rule validation
- 🏗️ **Layered Architecture**: Clean separation of concerns
- 📊 **Query Optimization**: Efficient database queries with JOIN FETCH

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|----------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.5.4 | Application Framework |
| **Spring Data JPA** | 3.5.4 | Data Access Layer |
| **Spring Web** | 3.5.4 | REST API Framework |
| **PostgreSQL** | 15+ | Database |
| **Maven** | 3.8+ | Build Tool |
| **Hibernate** | 6.4+ | ORM Framework |

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │───▶│    Service      │───▶│   Repository    │───▶│    Database     │
│     Layer       │    │     Layer       │    │     Layer       │    │   PostgreSQL    │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
        │                       │                       │                       │
   REST Endpoints         Business Logic          Data Access              Data Storage
   HTTP Handling          Validation Rules       JPA Queries              Persistence
   Response Mapping       Transaction Mgmt       Entity Mapping           ACID Properties
```

## 📁 Project Structure

```
student_course_management_system/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/student_course_management_system/
│   │   │   ├── 📄 StudentCourseManagementSystemApplication.java  # Main Application
│   │   │   ├── 📁 controller/                                    # REST Controllers
│   │   │   │   ├── 📄 StudentController.java                     # Student endpoints
│   │   │   │   ├── 📄 CourseController.java                      # Course endpoints
│   │   │   │   └── 📄 EnrollmentController.java                  # Enrollment endpoints
│   │   │   ├── 📁 service/                                       # Business Logic
│   │   │   │   ├── 📄 StudentService.java                        # Student service interface
│   │   │   │   ├── 📄 CourseService.java                         # Course service interface
│   │   │   │   ├── 📄 EnrollmentService.java                     # Enrollment service interface
│   │   │   │   └── 📁 Impl/                                      # Service implementations
│   │   │   │       ├── 📄 StudentServiceImpl.java               # Student business logic
│   │   │   │       ├── 📄 CourseServiceImpl.java                # Course business logic
│   │   │   │       └── 📄 EnrollmentServiceImpl.java            # Enrollment business logic
│   │   │   ├── 📁 repository/                                    # Data Access Layer
│   │   │   │   ├── 📄 StudentRepository.java                     # Student data operations
│   │   │   │   ├── 📄 CourseRepository.java                      # Course data operations
│   │   │   │   └── 📄 EnrollmentRepository.java                  # Enrollment data operations
│   │   │   └── 📁 domain/                                        # Entity Classes
│   │   │       ├── 📄 Student.java                              # Student entity
│   │   │       ├── 📄 Course.java                               # Course entity
│   │   │       └── 📄 Enrollment.java                           # Enrollment entity
│   │   └── 📁 resources/
│   │       ├── 📄 application.properties                        # Configuration
│   │       ├── 📁 static/                                       # Static resources
│   │       └── 📁 templates/                                    # Templates
│   └── 📁 test/                                                 # Test Classes
│       └── 📁 java/com/student_course_management_system/
│           └── 📄 StudentCourseManagementSystemApplicationTests.java
├── 📁 target/                                                   # Compiled classes
├── 📄 pom.xml                                                   # Maven configuration
├── 📄 README.md                                                 # Project documentation
├── 📄 .gitignore                                                # Git ignore rules
└── 📄 mvnw, mvnw.cmd                                            # Maven wrapper
```

## 🗄️ Database Schema

### Entity Relationship Diagram
```
┌─────────────────┐         ┌─────────────────┐         ┌─────────────────┐
│     STUDENTS    │         │   ENROLLMENTS   │         │     COURSES     │
├─────────────────┤         ├─────────────────┤         ├─────────────────┤
│ 🔑 id (PK)      │◄────────┤ 🔑 id (PK)      │────────►│ 🔑 id (PK)      │
│ 📝 name         │         │ 🔗 student_id   │         │ 📝 name         │
│ 🎯 roll_number  │         │ 🔗 course_id    │         │ 🏷️ course_code  │
│ 📧 email        │         │ 📅 enrollment_  │         │ 📚 course_title │
└─────────────────┘         │    date         │         │ ⭐ credits      │
                            └─────────────────┘         └─────────────────┘
```

### Table Specifications

#### Students Table
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| name | VARCHAR(255) | NOT NULL | Student full name |
| roll_number | BIGINT | UNIQUE, NOT NULL | Student roll number |
| email | VARCHAR(255) | NOT NULL | Student email address |

#### Courses Table
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| name | VARCHAR(255) | NOT NULL | Course name |
| course_code | VARCHAR(50) | UNIQUE, NOT NULL | Course code |
| course_title | VARCHAR(255) | UNIQUE, NOT NULL | Course title |
| credits | INTEGER | CHECK (credits >= 1 AND credits <= 5) | Course credits |

#### Enrollments Table
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| student_id | BIGINT | FOREIGN KEY, NOT NULL | Reference to student |
| course_id | BIGINT | FOREIGN KEY, NOT NULL | Reference to course |
| enrollment_date | DATE | NOT NULL | Date of enrollment |

## 🚀 Installation

### Prerequisites
- ☕ **Java 21** or higher
- 🗄️ **PostgreSQL 15** or higher
- 🔧 **Maven 3.8** or higher
- 🌐 **Git** (for cloning)

### Step-by-Step Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd student_course_management_system
   ```

2. **Database Setup**
   ```sql
   -- Connect to PostgreSQL as superuser
   CREATE DATABASE student_course_db;
   CREATE USER app_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE student_course_db TO app_user;
   ```

3. **Configure Application**
   ```bash
   # Edit src/main/resources/application.properties
   cp application.properties.example application.properties
   ```

4. **Build and Run**
   ```bash
   # Clean and compile
   mvn clean compile
   
   # Run the application
   mvn spring-boot:run
   ```

5. **Verify Installation**
   ```bash
   curl http://localhost:8080/api/students
   # Should return: []
   ```

## ⚙️ Configuration

### Application Properties
```properties
# Application Configuration
spring.application.name=student_course_management_system
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/student_course_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Logging Configuration
logging.level.root=WARN
logging.level.org.springframework=WARN
logging.level.com.student_course_management_system=INFO
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Student Endpoints

#### Create Student
```http
POST /api/students
Content-Type: application/json

{
  "name": "John Doe",
  "rollNumber": 12345,
  "email": "john.doe@example.com"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "rollNumber": 12345,
  "email": "john.doe@example.com"
}
```

#### Get All Students
```http
GET /api/students
```

#### Get Student by ID
```http
GET /api/students/{id}
```

#### Get Student by Roll Number
```http
GET /api/students/rollNumber/{rollNumber}
```

#### Update Student
```http
PUT /api/students/{id}
Content-Type: application/json

{
  "name": "John Smith",
  "rollNumber": 12345,
  "email": "john.smith@example.com"
}
```

#### Delete Student
```http
DELETE /api/students/{id}
```

### Course Endpoints

#### Create Course
```http
POST /api/courses
Content-Type: application/json

{
  "name": "Data Structures",
  "courseCode": "CS101",
  "courseTitle": "Introduction to Data Structures",
  "credits": 3
}
```

#### Get All Courses
```http
GET /api/courses
```

#### Get Course by ID
```http
GET /api/courses/{id}
```

#### Get Course by Code
```http
GET /api/courses/code/{courseCode}
```

#### Get Course by Title
```http
GET /api/courses/title/{courseTitle}
```

#### Get Courses by Credits
```http
GET /api/courses/credits/{credits}
```

#### Update Course
```http
PUT /api/courses/{id}
```

#### Delete Course
```http
DELETE /api/courses/{id}
```

### Enrollment Endpoints

#### Enroll Student
```http
POST /api/enrollments/enroll?studentId=1&courseId=1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "student": {
    "id": 1,
    "name": "John Doe",
    "rollNumber": 12345,
    "email": "john.doe@example.com"
  },
  "course": {
    "id": 1,
    "name": "Data Structures",
    "courseCode": "CS101",
    "courseTitle": "Introduction to Data Structures",
    "credits": 3
  },
  "enrollmentDate": "2024-01-15"
}
```

#### Get All Enrollments
```http
GET /api/enrollments
```

#### Get Enrollment by ID
```http
GET /api/enrollments/{id}
```

#### Get Student's Enrollments
```http
GET /api/enrollments/student/{studentId}
```

#### Get Course Enrollments
```http
GET /api/enrollments/course/{courseId}
```

#### Check Enrollment Exists
```http
GET /api/enrollments/exists?studentId=1&courseId=1
```

#### Update Enrollment
```http
PUT /api/enrollments/{id}
```

#### Delete Enrollment
```http
DELETE /api/enrollments/{id}
```

#### Unenroll Student
```http
DELETE /api/enrollments/unenroll?studentId=1&courseId=1
```

## 🔒 Business Rules

### Student Rules
- ✅ **Unique Roll Numbers**: Each student must have a unique roll number
- ✅ **Required Fields**: Name, roll number, and email are mandatory
- ✅ **Email Format**: Valid email format required
- ✅ **Cascade Protection**: Cannot delete student with active enrollments

### Course Rules
- ✅ **Unique Course Codes**: Each course must have a unique course code
- ✅ **Unique Course Titles**: Each course must have a unique title
- ✅ **Credits Validation**: Credits must be between 1 and 5 (inclusive)
- ✅ **Required Fields**: All fields are mandatory
- ✅ **Cascade Protection**: Cannot delete course with active enrollments

### Enrollment Rules
- ✅ **No Duplicate Enrollments**: Student cannot enroll in the same course twice
- ✅ **Referential Integrity**: Student and course must exist before enrollment
- ✅ **Auto Date Assignment**: Enrollment date is automatically set to current date
- ✅ **Cascade Operations**: Proper handling of student/course deletions

## 🧪 Testing

### Manual Testing with cURL

```bash
# Test Student Creation
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Johnson","rollNumber":1001,"email":"alice@example.com"}'

# Test Course Creation
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{"name":"Java Programming","courseCode":"CS201","courseTitle":"Advanced Java Programming","credits":4}'

# Test Enrollment
curl -X POST "http://localhost:8080/api/enrollments/enroll?studentId=1&courseId=1"

# Test Get All Students
curl http://localhost:8080/api/students
```

### Unit Testing
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=StudentServiceTest

# Run with coverage
mvn test jacoco:report
```

## 🤝 Contributing

### Development Workflow
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Standards
- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Maintain test coverage above 80%
- Follow REST API best practices

### Commit Message Format
```
type(scope): description

feat(student): add email validation
fix(enrollment): resolve duplicate enrollment bug
docs(readme): update API documentation
test(course): add unit tests for course service
```

**Made with ❤️ using Spring Boot**