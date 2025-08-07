<div align="center">

# 🎓 Student Course Management System

<img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=30&duration=3000&pause=1000&color=36BCF7&center=true&vCenter=true&width=600&lines=Student+Management+Made+Simple;Spring+Boot+%2B+PostgreSQL;Enterprise+Ready+Solution" alt="Typing SVG" />

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Latest-red?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

<img src="https://user-images.githubusercontent.com/74038190/212284100-561aa473-3905-4a80-b561-0d28506553ee.gif" width="700">

</div>

---

## 🌟 **What Makes This Special?**

<table>
<tr>
<td width="50%">

### 🚀 **Lightning Fast**
> Built with Spring Boot 3.5.4 for maximum performance

### 🔒 **Enterprise Ready**
> Production-grade validation and error handling

### 🎯 **Developer Friendly**
> Clean architecture with comprehensive APIs

### 🛡️ **Advanced Validation**
> Jakarta Bean Validation with custom business rules

</td>
<td width="50%">

### 📊 **Real-time Management**
> Monitor students, courses, and enrollments seamlessly

### 🔄 **Full CRUD Operations**
> Complete lifecycle management for all entities

### 🎨 **RESTful Design**
> Beautiful, intuitive API endpoints

### 📦 **Structured Responses**
> Enhanced DTOs with detailed error handling

</td>
</tr>
</table>

---

## 🏗️ **Architecture Overview**

<div align="center">

```mermaid
graph TB
    A[🌐 REST Controllers] --> H[🛡️ Global Exception Handler]
    A --> I[📦 DTOs]
    I --> J[🔄 Mappers]
    J --> B[⚙️ Service Layer]
    B --> C[🗄️ Repository Layer]
    C --> D[🐘 PostgreSQL Database]
    
    E[🎓 Student Entity] --> C
    F[📚 Course Entity] --> C
    G[🔗 Many-to-Many Relations] --> C
    
    style A fill:#ff6b6b,stroke:#333,stroke-width:2px,color:#fff
    style H fill:#e74c3c,stroke:#333,stroke-width:2px,color:#fff
    style I fill:#f39c12,stroke:#333,stroke-width:2px,color:#fff
    style J fill:#9b59b6,stroke:#333,stroke-width:2px,color:#fff
    style B fill:#4ecdc4,stroke:#333,stroke-width:2px,color:#fff
    style C fill:#45b7d1,stroke:#333,stroke-width:2px,color:#fff
    style D fill:#96ceb4,stroke:#333,stroke-width:2px,color:#fff
```

</div>

---

## ✨ **Core Features**

<details>
<summary>🎓 <strong>Student Management System</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 📝 **Create Students** | Enhanced validation with pattern matching | ✅ |
| 🔍 **Smart Search** | Find by ID, roll number with validation | ✅ |
| 📊 **Profile Management** | Complete CRUD with input sanitization | ✅ |
| 🔗 **Enrollment Tracking** | Many-to-many course relationships | ✅ |
| 📦 **DTO Support** | Request/Response DTOs with validation | ✅ |
| 🛡️ **Input Validation** | Jakarta Bean + custom business rules | ✅ |
| 🔄 **Path Validation** | URL parameter validation | ✅ |

</details>

<details>
<summary>📚 <strong>Course Management Hub</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 🏗️ **Course CRUD** | Complete course lifecycle management | ✅ |
| 🔎 **Advanced Search** | Find by code, title, credits | ✅ |
| 📈 **Credit Validation** | 1-6 credits with business rules | ✅ |
| 🔗 **Student Relations** | Courses with enrolled students | ✅ |
| 📦 **Enhanced DTOs** | Pattern validation for course codes | ✅ |
| 🛡️ **Format Validation** | Course code pattern matching | ✅ |

</details>

<details>
<summary>🔗 <strong>Enrollment Management Portal</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 📝 **Enroll Students** | Direct many-to-many enrollment | ✅ |
| 🚫 **Duplicate Prevention** | No duplicate enrollments allowed | ✅ |
| 🔄 **Unenroll Students** | Safe unenrollment with validation | ✅ |
| 📊 **Relationship Tracking** | Bidirectional entity relationships | ✅ |
| 🔍 **JOIN FETCH Queries** | Prevent N+1 query problems | ✅ |
| 🛡️ **Transaction Safety** | @Transactional for data consistency | ✅ |

</details>

---

## 🔥 **Enhanced Validation & Exception Handling**

<div align="center">

### 🛡️ **Comprehensive Error Management**

</div>

| Exception Type | HTTP Status | Description |
|---------------|-------------|-------------|
| 🔍 `ResourceNotFoundException` | `404 NOT FOUND` | Resource not found |
| ❌ `MethodArgumentNotValidException` | `400 BAD REQUEST` | Bean validation errors |
| 🔄 `ConstraintViolationException` | `400 BAD REQUEST` | Path variable validation |
| 💾 `MethodArgumentTypeMismatchException` | `400 BAD REQUEST` | Type conversion errors |
| 🛡️ `DuplicateResourceException` | `409 CONFLICT` | Duplicate resource |
| 🔄 `ValidationException` | `400 BAD REQUEST` | Custom business validation |
| 🌐 `Exception` | `500 INTERNAL ERROR` | Generic error handling |

**Key Features:**
- ✨ Structured error responses with timestamps
- 📝 Detailed validation error collection
- 🔍 Field-level error messages
- 🛡️ Path variable validation
- 🔄 Input sanitization in DTOs

---

## 🛠️ **API Endpoints**

<div align="center">

### 🎓 **Student Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟢 `GET` | `/api/students/` | Fetch all students | `200 OK` |
| 🟢 `GET` | `/api/students/{id}` | Get student by ID | `200 OK` |
| 🟢 `GET` | `/api/students/rollNumber/{rollNumber}` | Get by roll number | `200 OK` |
| 🟡 `POST` | `/api/students` | Create new student | `201 Created` |
| 🔵 `PUT` | `/api/students/{id}` | Update student | `200 OK` |
| 🔴 `DELETE` | `/api/students/{id}` | Delete student | `200 OK` |

<div align="center">

### 📚 **Course Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟢 `GET` | `/api/courses/` | All courses | `200 OK` |
| 🟢 `GET` | `/api/courses/{id}` | Course by ID | `200 OK` |
| 🟢 `GET` | `/api/courses/code/{courseCode}` | Course by code | `200 OK` |
| 🟢 `GET` | `/api/courses/title/{courseTitle}` | Course by title | `200 OK` |
| 🟢 `GET` | `/api/courses/credits/{credits}` | Courses by credits | `200 OK` |
| 🟡 `POST` | `/api/courses` | Create course | `201 Created` |
| 🔵 `PUT` | `/api/courses/{id}` | Update course | `200 OK` |
| 🔴 `DELETE` | `/api/courses/{id}` | Delete course | `200 OK` |

<div align="center">

### 🔗 **Enrollment Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟡 `POST` | `/api/students/{studentId}/enroll/{courseId}` | Enroll student | `200 OK` |
| 🔴 `DELETE` | `/api/students/{studentId}/unenroll/{courseId}` | Unenroll student | `200 OK` |
| 🟢 `GET` | `/api/students/{studentId}/courses` | Student's courses | `200 OK` |
| 🟢 `GET` | `/api/courses/{courseId}/students` | Course students | `200 OK` |

---

## 🚀 **Quick Start Guide**

<div align="center">

### 🎯 **Get Up and Running in 5 Minutes!**

</div>

```bash
# 1️⃣ Clone the repository
git clone <repository-url>
cd student_course_management_system

# 2️⃣ Start PostgreSQL
# Ensure PostgreSQL is running on localhost:5432

# 3️⃣ Create database
psql -U postgres -c "CREATE DATABASE student_course_db;"

# 4️⃣ Configure credentials (if needed)
# Edit src/main/resources/application.properties

# 5️⃣ Run the application
./mvnw spring-boot:run

# 🎉 That's it! Your app is running on http://localhost:8080
```

---

## 🎯 **Usage Examples**

<details>
<summary>🎓 <strong>Creating a Student</strong></summary>

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "rollNumber": 12345,
    "email": "john.doe@example.com"
  }'
```

</details>

<details>
<summary>📚 <strong>Creating a Course</strong></summary>

```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "courseCode": "CS101",
    "courseTitle": "Introduction to Data Structures",
    "credits": 3
  }'
```

</details>

<details>
<summary>🔗 <strong>Enrolling Student in Course</strong></summary>

```bash
# Enroll student ID 1 in course ID 1
curl -X POST http://localhost:8080/api/students/1/enroll/1
```

</details>

---

<div align="center">

## 🌟 **Why Choose Student Course Management System?**

### 💡 **Built with Excellence, Powered by Innovation**

*"Education is the most powerful weapon which you can use to change the world"* - Nelson Mandela

---

### 🤝 **Contributing**

Please feel free to submit a Pull Request.

</div>