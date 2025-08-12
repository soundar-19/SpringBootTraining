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
- 🔐 **Security**: Spring Security integration
- 💾 **Database**: H2 in-memory database with JPA/Hibernate

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|----------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.5.4 | Framework |
| **Spring Data JPA** | - | Data Access |
| **Spring Security** | - | Authentication & Authorization |
| **H2 Database** | - | In-Memory Database |
| **Maven** | 3.6+ | Build Tool |

## 📁 Project Structure

```
hostel_management_system/
├── src/
│   ├── main/
│   │   ├── java/com/example/hostel_management_system/
│   │   │   ├── config/
│   │   │   │   ├── DataInitializer.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── HostelController.java
│   │   │   │   ├── RoomController.java
│   │   │   │   ├── StaffController.java
│   │   │   │   └── StudentController.java
│   │   │   ├── domain/
│   │   │   │   ├── Hostel.java
│   │   │   │   ├── Room.java
│   │   │   │   ├── Staff.java
│   │   │   │   └── Student.java
│   │   │   ├── dto/
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

### 🔐 Authentication
> The application uses Spring Security. Ensure proper authentication headers are included in requests.

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

### Get All Students
```http
GET /api/students
```
**Response:** List of all students

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

### Get Students Without Staff
```http
GET /api/students/filter/without-staff
```
**Response:** List of students not assigned to any staff

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

## 🚨 Error Handling

| Exception | Description | HTTP Status |
|-----------|-------------|-------------|
| `ResourceNotFoundException` | Resource not found | 404 |
| `DuplicateResourceException` | Duplicate resource creation | 400 |
| `RoomNotAvailableException` | Room booking unavailable | 400 |

## 🛠️ Development

### Commands
```bash
# Run tests
mvn test

# Build application
mvn clean package

# Run with profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 🗄️ Database Schema
- **Strategy:** `create-drop` (auto-created on startup, dropped on shutdown)
- **Dialect:** H2Dialect
- **Show SQL:** Enabled in development

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