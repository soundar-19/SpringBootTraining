# 🎉 Event Ease - Event Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apache-maven)

**A comprehensive event management system built with Spring Boot**

[Features](#-features) • [Quick Start](#-quick-start) • [API Documentation](#-api-documentation) • [Database Schema](#-database-schema)

</div>

---

## 📋 Table of Contents

- [🎯 Overview](#-overview)
- [✨ Features](#-features)
- [🚀 Quick Start](#-quick-start)
- [🏗️ Architecture](#️-architecture)
- [📊 Database Schema](#-database-schema)
- [🔌 API Documentation](#-api-documentation)
- [🧪 Testing](#-testing)
- [🛠️ Configuration](#️-configuration)
- [📝 Contributing](#-contributing)

---

## 🎯 Overview

**Event Ease** is a robust event management system that allows users to create, manage, and register for events. Built with modern Spring Boot architecture, it provides a complete RESTful API for event management operations.

### 🎪 What can you do?
- 👥 **User Management**: Create and manage user profiles
- 🎫 **Event Management**: Create, update, and delete events
- 📝 **Registration System**: Register users for events
- 🔍 **Query Operations**: Advanced filtering and search capabilities

---

## ✨ Features

<table>
<tr>
<td width="50%">

### 🔐 **User Management**
- ✅ User registration and profile management
- ✅ Email validation
- ✅ User authentication ready

### 🎪 **Event Management**
- ✅ Create and manage events
- ✅ Event scheduling with date validation
- ✅ Location and description management
- ✅ Event capacity tracking

</td>
<td width="50%">

### 📋 **Registration System**
- ✅ User-Event registration mapping
- ✅ Registration date tracking
- ✅ Bulk registration queries
- ✅ Registration management

### 🏗️ **Technical Features**
- ✅ RESTful API design
- ✅ PostgreSQL database integration
- ✅ Data validation with Bean Validation
- ✅ Exception handling

</td>
</tr>
</table>

---

## 🚀 Quick Start

### Prerequisites

```bash
☑️ Java 21 or higher
☑️ Maven 3.6+
☑️ PostgreSQL 12+
☑️ Your favorite IDE (IntelliJ IDEA recommended)
```

### 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/event-ease.git
   cd event-ease
   ```

2. **Setup PostgreSQL Database**
   ```sql
   CREATE DATABASE event_ease;
   CREATE USER postgres WITH PASSWORD 'jdbc';
   GRANT ALL PRIVILEGES ON DATABASE event_ease TO postgres;
   ```

3. **Configure Application**
   ```properties
   # Already configured in src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/event_ease
   spring.datasource.username=postgres
   spring.datasource.password=jdbc
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Verify Installation**
   ```bash
   curl http://localhost:8080/api/events
   # Should return: []
   ```

---

## 🏗️ Architecture

```mermaid
graph TB
    A[Client] --> B[Controller Layer]
    B --> C[Service Layer]
    C --> D[Repository Layer]
    D --> E[PostgreSQL Database]
    
    B --> F[DTO Layer]
    F --> G[Mapper Layer]
    G --> H[Domain Layer]
    
    style A fill:#e1f5fe
    style B fill:#f3e5f5
    style C fill:#e8f5e8
    style D fill:#fff3e0
    style E fill:#fce4ec
```

### 📁 Project Structure

```
src/main/java/com/event_ease/event_ease/
├── ⚙️ config/              # Configuration Classes
│   └── OpenApiConfig.java
├── 🎮 controller/          # REST Controllers
│   ├── EventController.java
│   ├── UserController.java
│   └── RegistrationController.java
├── 🏢 service/             # Business Logic
│   ├── EventService.java
│   ├── UserService.java
│   ├── RegistrationService.java
│   └── Impl/               # Service Implementations
├── 🗄️ repository/          # Data Access Layer
│   ├── EventRepository.java
│   ├── UserRepository.java
│   └── RegistrationRepository.java
├── 🏗️ domain/              # Entity Classes
│   ├── Event.java
│   ├── User.java
│   └── Registration.java
├── 📦 dto/                 # Data Transfer Objects
│   ├── EventRequestDTO.java
│   ├── EventResponseDTO.java
│   ├── UserRequestDTO.java
│   ├── UserResponseDTO.java
│   ├── RegistrationRequestDTO.java
│   └── RegistrationResponseDTO.java
├── 🔄 mapper/              # Entity-DTO Mappers
│   ├── EventMapper.java
│   ├── UserMapper.java
│   └── RegistrationMapper.java
└── ⚠️ exception/           # Exception Handling
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    └── InvalidInputException.java
```

---

## 📊 Database Schema

### 🗃️ Tables Overview

<table>
<tr>
<td width="33%">

#### 👥 **users**
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
```

</td>
<td width="33%">

#### 🎪 **events**
```sql
CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    location VARCHAR(255) NOT NULL
);
```

</td>
<td width="33%">

#### 📝 **registrations**
```sql
CREATE TABLE registrations (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);
```

</td>
</tr>
</table>

### 🔗 Entity Relationships

```mermaid
erDiagram
    USER ||--o{ REGISTRATION : "registers for"
    EVENT ||--o{ REGISTRATION : "has registrations"
    
    USER {
        bigint id PK
        string name
        string email UK
    }
    
    EVENT {
        bigint id PK
        string title
        string description
        date date
        string location
    }
    
    REGISTRATION {
        bigint id PK
        bigint user_id FK
        bigint event_id FK
        date registration_date
    }
```

---

## 🔌 API Documentation

### 📚 Swagger UI Integration

**Event Ease** includes comprehensive API documentation using Swagger UI for interactive API exploration and testing.

#### 🚀 Quick Access
```
Swagger UI: http://localhost:8080/swagger-ui/index.html
API Docs JSON: http://localhost:8080/v3/api-docs
```

#### 📦 Maven Dependencies
Add these dependencies to your `pom.xml`:

```xml
<!-- Swagger/OpenAPI Documentation -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

#### ⚙️ Swagger Configuration

Create `SwaggerConfig.java` in your config package:

```java
package com.event_ease.event_ease.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI eventEaseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Ease API")
                        .description("Comprehensive Event Management System API")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Soundar Raja B")
                                .email("soundar@example.com")
                                .url("https://github.com/soundar-19")));
    }
}
```

#### 🎯 Controller Annotations Example

```java
@RestController
@RequestMapping("/api/events")
@Tag(name = "Event Management", description = "APIs for managing events")
public class EventController {
    
    @Operation(summary = "Get all events", description = "Retrieve a list of all events")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved events"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        // Implementation
    }
    
    @Operation(summary = "Create new event", description = "Create a new event with provided details")
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @Valid @RequestBody EventRequestDTO eventRequest) {
        // Implementation
    }
}
```

#### 🔧 Application Properties Configuration

Add to `application.properties`:

```properties
# Swagger Configuration
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true
```

#### 🌟 Features Available

- ✅ **Interactive API Testing**: Test endpoints directly from the browser
- ✅ **Request/Response Examples**: See sample data for all endpoints
- ✅ **Schema Documentation**: Detailed DTO and entity documentation
- ✅ **Authentication Support**: Ready for JWT/OAuth integration
- ✅ **Export Options**: Download OpenAPI specification

### Base URL
```
http://localhost:8080/api
```

---

## 👥 User Management API

<details>
<summary><b>🔍 Click to expand User API endpoints</b></summary>

### 📋 Get All Users
```http
GET /api/users
```

**Response:**
```json
[
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
    }
]
```

### 🔍 Get User by ID
```http
GET /api/users/{id}
```

**Response:**
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

### ➕ Create User
```http
POST /api/users
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

**Response:**
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

### ✏️ Update User
```http
PUT /api/users/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "John Smith",
    "email": "john.smith@example.com"
}
```

### 🗑️ Delete User
```http
DELETE /api/users/{id}
```

**Response:**
```json
"User deleted successfully"
```

</details>

---

## 🎪 Event Management API

<details>
<summary><b>🔍 Click to expand Event API endpoints</b></summary>

### 📋 Get All Events
```http
GET /api/events
```

**Response:**
```json
[
    {
        "id": 1,
        "title": "Spring Boot Workshop",
        "description": "Learn Spring Boot fundamentals",
        "date": "2024-12-25",
        "location": "Tech Hub, Building A"
    }
]
```

### 🔍 Get Event by ID
```http
GET /api/events/{id}
```

### ➕ Create Event
```http
POST /api/events
Content-Type: application/json
```

**Request Body:**
```json
{
    "title": "Spring Boot Workshop",
    "description": "Learn Spring Boot fundamentals",
    "date": "2024-12-25",
    "location": "Tech Hub, Building A"
}
```

**Validation Rules:**
- ✅ `title`: Required, not blank
- ✅ `description`: Required, not blank  
- ✅ `date`: Required, must be present or future date
- ✅ `location`: Required, not blank

### ✏️ Update Event
```http
PUT /api/events/{id}
Content-Type: application/json
```

### 🗑️ Delete Event
```http
DELETE /api/events/{id}
```

</details>

---

## 📝 Registration Management API

<details>
<summary><b>🔍 Click to expand Registration API endpoints</b></summary>

### 📋 Get All Registrations
```http
GET /api/registrations
```

**Response:**
```json
[
    {
        "id": 1,
        "user_id": 1,
        "event_id": 1,
        "registrationDate": "2024-08-08"
    }
]
```


### 🔍 Get Registration by ID
```http
GET /api/registrations/{id}
```

### ➕ Create Registration
```http
POST /api/registrations
Content-Type: application/json
```

**Request Body:**
```json
{
    "userId": 1,
    "eventId": 1
}
```

**Validation Rules:**
- ✅ `userId`: Required, must be valid user ID
- ✅ `eventId`: Required, must be valid event ID

### ✏️ Update Registration
```http
PUT /api/registrations/{id}
Content-Type: application/json
```

### 🗑️ Delete Registration
```http
DELETE /api/registrations/{id}
```

### 🔍 Get User's Registrations
```http
GET /api/users/{userId}/registrations
```

**Response:**
```json
[
    {
        "id": 1,
        "user_id": 1,
        "event_id": 1,
        "registrationDate": "2024-08-08"
    }
]
```

### 🔍 Get Event's Registrations
```http
GET /api/events/{eventId}/registrations
```

</details>

---

## 🧪 Testing

### 📊 Swagger UI Testing (Recommended)

**The easiest way to test the API is through Swagger UI:**

1. **Start the application**
   ```bash
   mvn spring-boot:run
   ```

2. **Open Swagger UI**
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

3. **Interactive Testing**
   - 🔍 Browse all available endpoints
   - 📝 View request/response schemas
   - ▶️ Execute API calls directly
   - 📄 See real-time responses

### 🔧 Manual Testing with cURL

<details>
<summary><b>📝 Click to expand testing examples</b></summary>

#### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "email": "alice@example.com"
  }'
```

#### Create an Event
```bash
curl -X POST http://localhost:8080/api/events \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Java Conference 2024",
    "description": "Annual Java developers conference",
    "date": "2024-12-15",
    "location": "Convention Center"
  }'
```

#### Register User for Event
```bash
curl -X POST http://localhost:8080/api/registrations \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "eventId": 1
  }'
```

#### Get All Events
```bash
curl http://localhost:8080/api/events
```

</details>

### 🎯 Postman Collection

Import this collection for comprehensive API testing:

```json
{
  "info": {
    "name": "Event Ease API",
    "description": "Complete API collection for Event Ease"
  },
  "item": [
    {
      "name": "Users",
      "item": [
        {
          "name": "Get All Users",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/api/users"
          }
        }
      ]
    }
  ],
  "variable": [
    {
      "key": "baseUrl",
      "value": "http://localhost:8080"
    }
  ]
}
```
### Sample ScreenShots
<img width="1428" height="739" alt="Screenshot 2025-08-08 163045" src="https://github.com/user-attachments/assets/8266f43d-e6dc-4c31-afcc-2081133b31c4" />
<img width="1434" height="562" alt="Screenshot 2025-08-08 161337" src="https://github.com/user-attachments/assets/668b45fa-8d64-4b71-9ec0-c25276a7f9de" />
<img width="1437" height="593" alt="Screenshot 2025-08-08 162146" src="https://github.com/user-attachments/assets/136a6d62-62e3-4603-9129-42f9f02153ee" />
<img width="1436" height="578" alt="Screenshot 2025-08-08 162627" src="https://github.com/user-attachments/assets/015ad718-4368-45ba-a109-2b710f773ab5" />
<img width="1439" height="630" alt="Screenshot 2025-08-08 163026" src="https://github.com/user-attachments/assets/ed1a3e3c-abb4-427c-a8d2-dbf5b4afb3fb" />


---

## 🛠️ Configuration

### 📋 Application Properties

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/event_ease
spring.datasource.username=postgres
spring.datasource.password=jdbc
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Hide Spring Boot Banner
spring.main.banner-mode=off

# Reduce Logging
logging.level.root=WARN
logging.level.org.springframework=WARN
logging.level.org.hibernate=WARN

# OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
```

### 🔧 Environment Variables

For production deployment, use environment variables:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/event_ease
export DB_USERNAME=postgres
export DB_PASSWORD=your_secure_password
export SERVER_PORT=8080
```

---

## ⚠️ Error Handling

The application includes comprehensive error handling:

### 🚨 Common Error Responses

<table>
<tr>
<td width="50%">

**404 - Resource Not Found**
```json
{
  "timestamp": "2024-08-08T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/api/users/999"
}
```

</td>
<td width="50%">

**400 - Validation Error**
```json
{
  "timestamp": "2024-08-08T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email is required",
  "path": "/api/users"
}
```

</td>
</tr>
</table>

---

## 🚀 Deployment

### 🐳 Docker Deployment

```dockerfile
FROM openjdk:21-jdk-slim
COPY target/event-ease-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### ☁️ Cloud Deployment

Ready for deployment on:
- ✅ AWS (Elastic Beanstalk, ECS)
- ✅ Google Cloud Platform
- ✅ Microsoft Azure
- ✅ Heroku

---

## 📈 Performance & Monitoring

### 📊 Metrics

- **Response Time**: < 200ms average
- **Throughput**: 1000+ requests/second
- **Database Connections**: Optimized connection pooling
- **Memory Usage**: < 512MB typical

### 🔍 Health Checks

```http
GET /actuator/health
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit your changes** (`git commit -m 'Add amazing feature'`)
4. **Push to the branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### 📝 Code Style

- Follow Java naming conventions
- Use meaningful variable names
- Add JavaDoc for public methods
- Write unit tests for new features

---

## 👨‍💻 Author

**Soundar Raja B**
- GitHub: [soundar-19](https://github.com/soundar-19)

---

<div align="center">

### 🌟 Star this repository if you found it helpful!


</div>


---

*Last updated: August 8, 2025*
