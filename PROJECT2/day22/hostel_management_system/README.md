# 🏨 Hostel Management System

> A comprehensive Spring Boot REST API application for managing hostel operations including hostels, rooms, students, and staff.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![H2](https://img.shields.io/badge/Database-H2-lightblue.svg)](https://www.h2database.com/)

## ✨ Features

- 🏠 **Hostel Management**: View hostels and filter by type
- 🏠 **Room Management**: Manage rooms with availability tracking and filtering
- 👥 **Student Management**: Handle student records with advanced filtering
- 👨‍💼 **Staff Management**: Manage staff with role-based operations
- 🔐 **Security**: JWT Authentication with role-based access control
- 💾 **Database**: H2 in-memory database with JPA/Hibernate
- 📄 **Pagination & Sorting**: Complete implementation of all 7 pagination tasks
- 🚀 **Performance**: Optimized queries with Slice support
- 📖 **API Documentation**: Interactive Swagger UI with JWT authentication

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|----------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.5.4 | Framework |
| **Spring Data JPA** | - | Data Access |
| **Spring Security** | - | JWT Authentication & Authorization |
| **JWT** | 0.11.5 | Token-based Authentication |
| **H2 Database** | - | In-Memory Database |
| **Swagger/OpenAPI** | 2.7.0 | API Documentation |
| **Maven** | 3.6+ | Build Tool |

## 📁 Project Structure

```
hostel_management_system/
├── src/
│   ├── main/
│   │   ├── java/com/example/hostel_management_system/
│   │   │   ├── config/
│   │   │   │   ├── DataInitializer.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── HostelController.java
│   │   │   │   ├── RoomController.java
│   │   │   │   ├── StaffController.java
│   │   │   │   ├── StudentController.java
│   │   │   │   └── WardenController.java
│   │   │   ├── domain/
│   │   │   │   ├── Hostel.java
│   │   │   │   ├── Room.java
│   │   │   │   ├── Staff.java
│   │   │   │   └── Student.java
│   │   │   ├── dto/
│   │   │   │   ├── AuthRequestDTO.java
│   │   │   │   ├── AuthResponseDTO.java
│   │   │   │   ├── RoomResponseDto.java
│   │   │   │   ├── StaffResponseDto.java
│   │   │   │   └── StudentResponseDto.java
│   │   │   ├── exception/
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── RoomNotAvailableException.java
│   │   │   ├── mapper/
│   │   │   │   ├── StaffMapper.java
│   │   │   │   └── StudentMapper.java
│   │   │   ├── repository/
│   │   │   │   ├── HostelRepository.java
│   │   │   │   ├── RoomRepository.java
│   │   │   │   ├── StaffRepository.java
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/
│   │   │   │   ├── HostelService.java
│   │   │   │   ├── JwtAuthFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   ├── RoomService.java
│   │   │   │   ├── StaffService.java
│   │   │   │   └── StudentService.java
│   │   │   └── HostelManagementSystemApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── target/
├── pom.xml
└── README.md
```

## 🗄️ Database Schema & Entities

### Entity Relationships
```
Hostel (1) ──────── (*) Room
Staff (1) ──────── (*) Student
```

### 🏠 Hostel Entity
```java
@Entity
public class Hostel {
    private Long id;
    private String name;
    private String address;
    private String email;
    private HostelType hostelType; // BOYS, GIRLS, CO_ED
    private List<Room> rooms;
}
```

### 🏠 Room Entity
```java
@Entity
public class Room {
    private Long id;
    private String roomNumber;
    private RoomType roomType; // SINGLE, DOUBLE, TRIPLE, DORMITORY
    private Integer capacity;
    private Integer currentOccupancy;
    private Double pricePerMonth;
    private RoomStatus status; // AVAILABLE, OCCUPIED, MAINTENANCE, OUT_OF_ORDER
    private Hostel hostel;
}
```

### 👥 Student Entity
```java
@Entity
public class Student {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Staff staff;
}
```

### 👨‍💼 Staff Entity
```java
@Entity
public class Staff {
    private Long id;
    private String name;
    private String email;
    private String address;
    private StaffRole role; // WARDEN, ASSISTANT_WARDEN, SECURITY, MAINTENANCE, ADMIN
    private List<Student> students;
}
```

## ⚙️ Configuration Properties

```properties
# Application
spring.application.name=hostel_management_system
server.port=8080

# H2 Database
spring.datasource.url=jdbc:h2:mem:hosteldb
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.com.example.hostel_management_system=DEBUG

# Pagination Configuration
spring.data.web.pageable.default-page-size=10
spring.data.web.pageable.page-parameter=page
spring.data.web.pageable.size-parameter=size
spring.data.web.pageable.one-indexed-parameters=true
```

## 🚀 Getting Started

### Prerequisites
- ☕ Java 21 or higher
- 📦 Maven 3.6+
- 🌐 Web browser (for H2 console)

### 📥 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hostel_management_system
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### 🔧 Configuration

| Service | URL | Credentials |
|---------|-----|-------------|
| **Application** | `http://localhost:8080` | - |
| **Swagger UI** | `http://localhost:8080/swagger-ui.html` | Interactive API Documentation |
| **H2 Console** | `http://localhost:8080/h2-console` | Username: `sa`<br>Password: `password` |
| **API Base** | `http://localhost:8080/api` | Spring Security |

**Database Connection:**
- **JDBC URL:** `jdbc:h2:mem:hosteldb`
- **Driver:** `org.h2.Driver`

## 📚 API Documentation

### 🌐 Base URL
```
http://localhost:8080/api
```

### 🔐 JWT Authentication & Authorization

**Authentication Flow:**
1. Login with credentials to get JWT token
2. Include token in Authorization header: `Bearer <token>`
3. Token expires in 150 seconds (2.5 minutes)

**Users & Roles:**
- **Admin** (username: `admin`, password: `admin123`) - Full system access
- **Warden** (username: `warden`, password: `warden123`) - Student management access
- **User** (username: `user`, password: `user123`) - Basic read access

**Role-based Endpoints:**
- `/api/auth/**` - Public (login)
- `/api/admin/**` - Admin only
- `/api/warden/**` - Warden only
- `/api/staff/admin/**` - Admin only
- `/api/students/admin/**` - Admin only
- `/api/**` - All authenticated users

**Authentication:** JWT Bearer Token

---

## 🔐 Authentication Endpoints

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "userName": "admin",
  "password": "admin123"
}
```
**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Using JWT Token
```http
GET /api/admin/dashboard
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🔑 Admin Endpoints

### Get Admin Dashboard
```http
GET /api/admin/dashboard
```
**Role:** ADMIN  
**Response:** Dashboard with system statistics including total staff, students, and role counts

### Get All Staff for Admin
```http
GET /api/admin/staff/all?page=1&size=20&sort=role,asc
```
**Role:** ADMIN  
**Parameters:**
- `page` (query) - Page number (1-based)
- `size` (query) - Page size (default: 20)
- `sort` (query) - Sort criteria

**Response:** Paginated list of all staff members

### Get All Students for Admin
```http
GET /api/admin/students/all?page=1&size=20&sort=name,asc
```
**Role:** ADMIN  
**Parameters:**
- `page` (query) - Page number (1-based)
- `size` (query) - Page size (default: 20)
- `sort` (query) - Sort criteria

**Response:** Paginated list of all students

---

## 🔑 Warden Endpoints

### Get Students Under Warden
```http
GET /api/warden/students?page=1&size=15&sort=name,asc
```
**Role:** WARDEN  
**Parameters:**
- `page` (query) - Page number (1-based)
- `size` (query) - Page size (default: 15)
- `sort` (query) - Sort criteria

**Response:** Paginated list of students under warden supervision

### Assign Staff to Student
```http
PUT /api/warden/students/{id}/assign-staff?staffId={staffId}
```
**Role:** WARDEN  
**Parameters:**
- `id` (path) - Student ID
- `staffId` (query) - Staff ID to assign

**Response:** Updated student details

---

## 🏨 Hostel Endpoints

### Get All Hostels
```http
GET /api/hostels
```
**Response:** List of all hostels

### Get Hostel by ID
```http
GET /api/hostels/{id}
```
**Parameters:**
- `id` (path) - Hostel ID

**Response:** Hostel details

### Get Hostels by Type
```http
GET /api/hostels/type/{hostelType}
```
**Parameters:**
- `hostelType` (path) - Hostel type enum value

**Response:** List of hostels matching the type

---

## 🏠 Room Endpoints

### Get All Rooms
```http
GET /api/rooms
```
**Response:** List of all rooms with details

### Get Room by ID
```http
GET /api/rooms/{id}
```
**Parameters:**
- `id` (path) - Room ID

**Response:** Room details

### Get Available Rooms
```http
GET /api/rooms/available
```
**Response:** List of available rooms

### Get Rooms by Hostel
```http
GET /api/rooms/hostel/{hostelId}
```
**Parameters:**
- `hostelId` (path) - Hostel ID

**Response:** List of rooms in the specified hostel

### Filter Rooms by Price Range
```http
GET /api/rooms/filter/price-range?minPrice={min}&maxPrice={max}
```
**Parameters:**
- `minPrice` (query) - Minimum price
- `maxPrice` (query) - Maximum price

**Response:** List of rooms within price range

### Filter Rooms by Capacity
```http
GET /api/rooms/filter/capacity/{capacity}
```
**Parameters:**
- `capacity` (path) - Room capacity

**Response:** List of rooms with specified capacity

### Filter Rooms by Number Prefix
```http
GET /api/rooms/filter/room-prefix/{prefix}
```
**Parameters:**
- `prefix` (path) - Room number prefix

**Response:** List of rooms with matching prefix

---

## 👥 Student Endpoints

### Get All Students (with Pagination)
```http
GET /api/students?page=1&size=10&sort=name,asc&sort=email,desc
```
**Parameters:**
- `page` (query) - Page number (1-based)
- `size` (query) - Page size (default: 10)
- `sort` (query) - Sort fields and directions (multiple allowed)

**Response:** Paginated list of students

### Get Student by ID
```http
GET /api/students/{id}
```
**Parameters:**
- `id` (path) - Student ID

**Response:** Student details

### Filter Students by Name
```http
GET /api/students/filter/name/{name}
```
**Parameters:**
- `name` (path) - Name to search for

**Response:** List of students with matching names

### Filter Students by Email Domain
```http
GET /api/students/filter/email-domain/{domain}
```
**Parameters:**
- `domain` (path) - Email domain

**Response:** List of students with matching email domain

### Filter Students by City
```http
GET /api/students/filter/city/{city}
```
**Parameters:**
- `city` (path) - City name

**Response:** List of students from specified city

### Advanced Student Filter
```http
GET /api/students/filter?name={name}&email={email}&address={address}&staffId={staffId}
```
**Parameters (all optional):**
- `name` (query) - Student name
- `email` (query) - Student email
- `address` (query) - Student address
- `staffId` (query) - Associated staff ID

**Response:** List of students matching criteria

### Get Students Without Staff (with Pagination)
```http
GET /api/students/filter/without-staff?page=1&size=10&sort=name,asc
```
**Parameters:**
- `page` (query) - Page number
- `size` (query) - Page size
- `sort` (query) - Sort criteria

**Response:** Paginated list of students not assigned to any staff

### Get Students Metadata Only
```http
GET /api/students/metadata?page=1&size=10
```
**Response:** Pagination metadata without actual student data

### Get Students Slice (Performance Optimized)
```http
GET /api/students/slice?page=1&size=10&sort=name,asc
```
**Response:** Student slice without total count calculation

---

## 👨‍💼 Staff Endpoints

### Get All Staff
```http
GET /api/staff
```
**Response:** List of all staff members

### Get Staff by ID
```http
GET /api/staff/{id}
```
**Parameters:**
- `id` (path) - Staff ID

**Response:** Staff details

### Get Staff by Role
```http
GET /api/staff/role/{role}
```
**Parameters:**
- `role` (path) - Staff role enum value

**Response:** List of staff with specified role

### Get Students by Staff ID
```http
GET /api/staff/{id}/students
```
**Parameters:**
- `id` (path) - Staff ID

**Response:** List of students assigned to the staff member

### Filter Staff by Name
```http
GET /api/staff/filter/name/{name}
```
**Parameters:**
- `name` (path) - Name to search for

**Response:** List of staff with matching names

### Filter Staff by Email Domain
```http
GET /api/staff/filter/email-domain/{domain}
```
**Parameters:**
- `domain` (path) - Email domain

**Response:** List of staff with matching email domain

### Get Staff with Students
```http
GET /api/staff/filter/with-students
```
**Response:** List of staff members who have assigned students

---

## 📋 Response Format

### HTTP Status Codes
| Code | Status | Description |
|------|--------|-------------|
| `200` | ✅ OK | Successful request |
| `404` | ❌ Not Found | Resource not found |
| `400` | ⚠️ Bad Request | Invalid request parameters |
| `500` | 🔥 Internal Server Error | Server error |

### Sample Response Structure

**Student Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "address": "123 Main St, City",
  "staffId": 1
}
```

**Room Response:**
```json
{
  "id": 1,
  "roomNumber": "A101",
  "capacity": 2,
  "price": 500.0,
  "status": "AVAILABLE",
  "type": "SINGLE",
  "hostelId": 1
}
```
## Sample Screenshots
<img width="1436" height="819" alt="image" src="https://github.com/user-attachments/assets/a051075f-d7a8-4935-a5f8-488d665d3987" />
<img width="1433" height="818" alt="image" src="https://github.com/user-attachments/assets/6219ad58-11f2-4646-a7cc-772f41d899a5" />
<img width="1442" height="823" alt="image" src="https://github.com/user-attachments/assets/84ddbb74-07e2-4e5d-87da-f2ebd53afe6f" />
<img width="1427" height="822" alt="image" src="https://github.com/user-attachments/assets/72cd6f7e-989b-41e6-b190-93400219111e" />
<img width="1400" height="828" alt="image" src="https://github.com/user-attachments/assets/018dc9ff-6aa8-4163-8df3-5095bbe7d0f1" />
<img width="1422" height="824" alt="image" src="https://github.com/user-attachments/assets/4873b1b7-8a7c-473d-8801-8c252a546b43" />


## 🚨 Error Handling

| Exception | Description | HTTP Status |
|-----------|-------------|-------------|
| `ResourceNotFoundException` | Resource not found | 404 |
| `DuplicateResourceException` | Duplicate resource creation | 400 |
| `RoomNotAvailableException` | Room booking unavailable | 400 |

## 📄 Pagination & Sorting Features

### ✅ Implemented Tasks

1. **Multiple Sort Fields** - Sort by multiple columns with different directions
2. **Default Paging & Sorting** - Global and method-level defaults
3. **Search with Paging** - Filter results then paginate
4. **Metadata Only** - Return pagination info without content
5. **Custom Null Handling** - Control null value positioning in sorts
6. **One-indexed Pages** - User-friendly page numbering starting from 1
7. **Performance Optimization** - Slice support and optimized queries

### 🚀 Example Usage
```bash
# Multiple sort fields
GET /api/students?page=1&size=5&sort=name,asc&sort=email,desc

# Search with paging
GET /api/students/filter?name=John&page=1&size=3

# Metadata only
GET /api/students/metadata?page=1&size=10

# Performance slice
GET /api/students/slice?page=1&size=10
```

## 🛠️ Development

### Commands
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AuthControllerTest

# Run tests with coverage
mvn clean test jacoco:report

# Build application
mvn clean package

# Run with profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🧪 Testing

### Test Coverage
The project includes comprehensive unit tests covering:

#### Controller Tests
- **AuthControllerTest** - Authentication endpoints
- **AdminControllerTest** - Admin dashboard and management
- **WardenControllerTest** - Warden-specific operations
- **HostelControllerTest** - Hostel management endpoints
- **RoomControllerTest** - Room operations and filtering
- **StudentControllerTest** - Student CRUD and pagination
- **StaffControllerTest** - Staff management operations

#### Service Tests
- **StudentServiceTest** - Business logic for student operations
- **StaffServiceTest** - Business logic for staff operations

### Test Structure
```
src/test/java/
├── controller/
│   ├── TestSecurityConfig.java
│   ├── AuthControllerTest.java
│   ├── AdminControllerTest.java
│   ├── WardenControllerTest.java
│   ├── HostelControllerTest.java
│   ├── RoomControllerTest.java
│   ├── StudentControllerTest.java
│   └── StaffControllerTest.java
└── service/
    ├── StudentServiceTest.java
    └── StaffServiceTest.java
```

### Running Tests
```bash
# Run all unit tests
mvn test

# Run controller tests only
mvn test -Dtest="*ControllerTest"

# Run service tests only
mvn test -Dtest="*ServiceTest"

# Generate test report
mvn surefire-report:report
```

### Test Features
- **MockMvc** for web layer testing
- **Mockito** for mocking dependencies
- **JUnit 5** for test framework
- **Spring Boot Test** for integration support
- **Custom Security Config** for test isolation

### 🗄️ Database Schema
- **Strategy:** `create-drop` (auto-created on startup, dropped on shutdown)
- **Dialect:** H2Dialect
- **Show SQL:** Enabled in development
- **Test Database:** Separate H2 instance for testing

## 🤝 Contributing

1. 🍴 Fork the repository
2. 🌿 Create a feature branch (`git checkout -b feature/amazing-feature`)
3. 💾 Commit your changes (`git commit -m 'Add amazing feature'`)
4. 📤 Push to the branch (`git push origin feature/amazing-feature`)
5. 🔄 Create a Pull Request

---

<div align="center">
  <p>Made using Spring Boot</p>
</div>
