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
- 📝 **Enrollment Management**: Direct Many-to-Many relationship with duplicate prevention
- 🔍 **Advanced Queries**: Search by roll number, course code, title, and credits
- 🛡️ **Data Integrity**: Referential integrity and business rule enforcement

### Technical Features
- 🚀 **RESTful API**: Clean REST endpoints with proper HTTP status codes
- 🗄️ **PostgreSQL Integration**: Robust database persistence with Hibernate ORM
- 🔄 **Many-to-Many Relationships**: Direct JPA entity relationships with join tables
- ✅ **Input Validation**: Jakarta validation with comprehensive business rules
- 🏗️ **Layered Architecture**: Clean separation of concerns with DTOs and mappers
- 📊 **Query Optimization**: JOIN FETCH queries to prevent N+1 problems
- 🛡️ **Global Exception Handling**: Centralized error management with custom exceptions
- 🔄 **Entity-DTO Mapping**: Manual mapping without Lombok dependency
- 🔒 **Transaction Management**: @Transactional for data consistency
- 🚫 **Lazy Loading Solutions**: Custom repository methods with eager fetching

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
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │───▶│      DTO        │───▶│    Service      │───▶│   Repository    │───▶│    Database     │
│     Layer       │    │   & Mapper      │    │     Layer       │    │     Layer       │    │   PostgreSQL    │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
        │                       │                       │                       │                       │
   REST Endpoints         Data Transfer           Business Logic          Data Access              Data Storage
   HTTP Handling          Entity Mapping          Enrollment Logic       JPA Queries              Persistence
   Exception Handling     Validation Rules        Transaction Mgmt       Entity Mapping           ACID Properties
```

## 📁 Project Structure

```
student_course_management_system/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/student_course_management_system/
│   │   │   ├── 📄 StudentCourseManagementSystemApplication.java  # Main Application
│   │   │   ├── 📁 controller/                                    # REST Controllers
│   │   │   │   ├── 📄 StudentController.java                     # Student & enrollment endpoints
│   │   │   │   └── 📄 CourseController.java                      # Course endpoints
│   │   │   ├── 📁 service/                                       # Business Logic
│   │   │   │   ├── 📄 StudentService.java                        # Student service interface
│   │   │   │   ├── 📄 CourseService.java                         # Course service interface
│   │   │   │   └── 📁 Impl/                                      # Service implementations
│   │   │   │       ├── 📄 StudentServiceImpl.java               # Student & enrollment logic
│   │   │   │       └── 📄 CourseServiceImpl.java                # Course business logic
│   │   │   ├── 📁 repository/                                    # Data Access Layer
│   │   │   │   ├── 📄 StudentRepository.java                     # Student data operations
│   │   │   │   └── 📄 CourseRepository.java                      # Course data operations
│   │   │   ├── 📁 domain/                                        # Entity Classes
│   │   │   │   ├── 📄 Student.java                              # Student entity
│   │   │   │   └── 📄 Course.java                               # Course entity
│   │   │   ├── 📁 dto/                                          # Data Transfer Objects
│   │   │   │   ├── 📄 StudentRequestDTO.java                     # Student request DTO
│   │   │   │   ├── 📄 StudentResponseDTO.java                    # Student response DTO
│   │   │   │   ├── 📄 CourseRequestDTO.java                      # Course request DTO
│   │   │   │   └── 📄 CourseResponseDTO.java                     # Course response DTO
│   │   │   ├── 📁 mapper/                                        # Entity-DTO Mappers
│   │   │   │   ├── 📄 StudentMapper.java                         # Student entity-DTO mapping
│   │   │   │   └── 📄 CourseMapper.java                          # Course entity-DTO mapping
│   │   │   └── 📁 exception/                                     # Exception Handling
│   │   │       ├── 📄 GlobalExceptionHandler.java               # Global exception handler
│   │   │       ├── 📄 ResourceNotFoundException.java            # 404 exceptions
│   │   │       ├── 📄 DuplicateResourceException.java           # 409 exceptions
│   │   │       └── 📄 InvalidInputException.java                # 400 exceptions
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
│     STUDENTS    │         │ STUDENT_COURSES │         │     COURSES     │
├─────────────────┤         ├─────────────────┤         ├─────────────────┤
│ 🔑 id (PK)      │◄────────┤ 🔗 student_id   │────────►│ 🔑 id (PK)      │
│ 📝 name         │         │ 🔗 course_id    │         │ 🏷️ course_code  │
│ 🎯 roll_number  │         └─────────────────┘         │ 📚 course_title │
│ 📧 email        │                                     │ ⭐ credits      │
└─────────────────┘                                     └─────────────────┘
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
| course_code | VARCHAR(50) | UNIQUE, NOT NULL | Course code |
| course_title | VARCHAR(255) | UNIQUE, NOT NULL | Course title |
| credits | INTEGER | CHECK (credits >= 1 AND credits <= 5) | Course credits |

#### Student_Courses Table (Join Table)
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| student_id | BIGINT | FOREIGN KEY, NOT NULL | Reference to student |
| course_id | BIGINT | FOREIGN KEY, NOT NULL | Reference to course |
| | | PRIMARY KEY (student_id, course_id) | Composite primary key |

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

### Performance Optimizations
- **JOIN FETCH Queries**: Prevents N+1 query problems
- **Lazy Loading Prevention**: Custom repository methods with eager fetching
- **Transaction Boundaries**: Proper @Transactional usage for data consistency
- **Constructor Injection**: Better performance than field injection
- **Manual DTO Mapping**: No reflection overhead from mapping libraries

### Database Optimizations
```sql
-- Indexes automatically created for:
-- Primary keys (id columns)
-- Unique constraints (roll_number, course_code, course_title)
-- Foreign keys (student_id, course_id in join table)

-- Composite primary key for join table prevents duplicates
PRIMARY KEY (student_id, course_id)
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
spring.jpa.hibernate.ddl-auto=update          # Auto-create/update tables
spring.jpa.show-sql=false                     # Hide SQL in production
spring.jpa.open-in-view=false                 # Prevent lazy loading issues
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
Content-Type: application/json

{
  "courseCode": "CS101",
  "courseTitle": "Advanced Data Structures",
  "credits": 4
}
```

#### Delete Course
```http
DELETE /api/courses/{id}
```

**Response (200 OK):**
```json
"Course deleted successfully"
```

### Enrollment Endpoints

#### Enroll Student in Course
```http
POST /api/students/{studentId}/enroll/{courseId}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "rollNumber": 12345,
  "email": "john.doe@example.com",
  "courses": [
    {
      "id": 1,
      "courseCode": "CS101",
      "courseTitle": "Introduction to Data Structures",
      "credits": 3
    }
  ]
}
```

#### Unenroll Student from Course
```http
DELETE /api/students/{studentId}/unenroll/{courseId}
```

#### Get Student's Courses
```http
GET /api/students/{studentId}/courses
```

#### Get Course Students
```http
GET /api/courses/{courseId}/students
```

## 🔧 Technical Implementation Details

### Entity Relationships
```java
// Student Entity (Owning Side)
@ManyToMany
@JoinTable(
    name = "student_courses",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();

// Course Entity (Inverse Side)
@ManyToMany(mappedBy = "courses")
private Set<Student> students = new HashSet<>();
```

### Custom Repository Methods
```java
// Prevent lazy initialization with JOIN FETCH
@Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
Optional<Student> findByIdWithCourses(@Param("id") Long id);

@Query("SELECT c FROM Course c LEFT JOIN FETCH c.students WHERE c.id = :id")
Optional<Course> findByIdWithStudents(@Param("id") Long id);
```

### DTO Pattern Implementation
```java
// Manual mapping without Lombok
public CourseResponseDTO toDTO(Course course) {
    CourseResponseDTO dto = new CourseResponseDTO();
    dto.setId(course.getId());
    dto.setCourseCode(course.getCourseCode());
    dto.setCourseTitle(course.getCourseTitle());
    dto.setCredits(course.getCredits());
    return dto;
}
```

### Transaction Management
```java
// Ensures data consistency for enrollment operations
@Transactional
public Student enrollInCourse(Long studentId, Long courseId) {
    // Uses JOIN FETCH to prevent lazy loading
    Student student = studentRepository.findByIdWithCourses(studentId).orElse(null);
    // Business logic with proper validation
}
```

### Validation Implementation
```java
// Jakarta Bean Validation
@NotBlank(message = "Course Code is required")
private String courseCode;

@Min(value = 1, message = "Credits must be at least 1")
@Max(value = 5, message = "Credits cannot exceed 5")
private Integer credits;
```

## 🔒 Business Rules & Exception Handling

### Student Rules
- ✅ **Unique Roll Numbers**: Each student must have a unique roll number
- ✅ **Required Fields**: Name, roll number, and email are mandatory
- ✅ **Email Format**: Valid email format required (Jakarta validation)
- ✅ **Roll Number Validation**: Cannot update to existing roll number
- ✅ **Existence Check**: Student must exist before enrollment operations

### Course Rules
- ✅ **Unique Course Codes**: Each course must have a unique course code
- ✅ **Unique Course Titles**: Each course must have a unique title
- ✅ **Credits Validation**: Credits must be between 1 and 5 (inclusive)
- ✅ **Required Fields**: All fields are mandatory
- ✅ **Update Validation**: Cannot update to existing code/title of another course
- ✅ **Existence Check**: Course must exist before enrollment operations

### Enrollment Rules
- ✅ **No Duplicate Enrollments**: Student cannot enroll in the same course twice
- ✅ **Referential Integrity**: Student and course must exist before enrollment
- ✅ **Many-to-Many Relationship**: Direct relationship with student_courses join table
- ✅ **Bidirectional Mapping**: Student owns the relationship, Course is mapped by
- ✅ **Lazy Loading Prevention**: JOIN FETCH queries for related entities

### Exception Handling & HTTP Status Codes
- 🔴 **404 NOT FOUND**: Resource not found (students, courses, empty collections)
  - `ResourceNotFoundException` → Returns appropriate error message
- 🟡 **409 CONFLICT**: Duplicate resources detected
  - `DuplicateResourceException` → Roll numbers, course codes, titles, enrollments
- 🟠 **400 BAD REQUEST**: Invalid input or validation failures
  - `InvalidInputException` → Invalid unenrollment attempts
  - Jakarta validation errors → Field validation failures
- 🔴 **500 INTERNAL ERROR**: Unexpected server errors
  - Global exception handler catches all unhandled exceptions

### Custom Exception Classes
```java
// Custom exceptions with meaningful messages
ResourceNotFoundException     // 404 - Entity not found
DuplicateResourceException   // 409 - Duplicate constraint violation
InvalidInputException        // 400 - Business logic validation failure
GlobalExceptionHandler       // Centralized exception handling
```

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
  -d '{"courseCode":"CS201","courseTitle":"Advanced Java Programming","credits":4}'

# Test Enrollment
curl -X POST "http://localhost:8080/api/students/1/enroll/1"

# Test Get Student Courses (with JOIN FETCH)
curl http://localhost:8080/api/students/1/courses

# Test Get Course Students (with JOIN FETCH)
curl http://localhost:8080/api/courses/1/students

# Test Duplicate Prevention
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Bob Smith","rollNumber":1001,"email":"bob@example.com"}'
# Should return 409 Conflict

# Test Validation
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{"courseCode":"CS999","courseTitle":"Invalid Course","credits":10}'
# Should return 400 Bad Request
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
- Use DTOs for data transfer (no Lombok dependency)
- Implement proper exception handling with custom exceptions
- Follow Jakarta validation standards
- Use @Transactional for data consistency
- Implement JOIN FETCH to prevent lazy loading issues
- Manual entity-DTO mapping for better control
- Constructor-based dependency injection

### Commit Message Format
```
type(scope): description

feat(student): add email validation
fix(enrollment): resolve duplicate enrollment bug
docs(readme): update API documentation
test(course): add unit tests for course service
```