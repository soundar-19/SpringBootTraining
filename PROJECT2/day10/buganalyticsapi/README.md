# Bug Analytics API

A Spring Boot REST API for tracking and analyzing software bugs across multiple projects. This application provides analytics endpoints to generate insights about bug distribution by project, status, and priority.

## Features

- **Project Management**: Track bugs across multiple software projects
- **Bug Analytics**: Get statistical insights about bug distribution
- **REST API**: Clean RESTful endpoints for data retrieval
- **PostgreSQL Integration**: Robust database storage with JPA/Hibernate
- **Real-time Analytics**: Live bug count aggregations

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Spring Boot Actuator**

## Database Schema

### Projects Table
```sql
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

### Bugs Table
```sql
CREATE TABLE bugs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    project_id BIGINT REFERENCES projects(id)
);
```

## API Endpoints

### Analytics Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/analytics/bugs-by-project` | Get bug count grouped by project |
| GET | `/api/analytics/bugs-by-status` | Get bug count grouped by status |
| GET | `/api/analytics/bugs-by-priority` | Get bug count grouped by priority |

### Response Examples

**GET /api/analytics/bugs-by-project**
```json
[
    {
        "projectName": "E-Commerce Platform",
        "bugCount": 5
    },
    {
        "projectName": "Mobile Banking App",
        "bugCount": 5
    }
]
```

**GET /api/analytics/bugs-by-status**
```json
[
    {
        "status": "OPEN",
        "count": 20
    },
    {
        "status": "IN_PROGRESS",
        "count": 15
    }
]
```

**GET /api/analytics/bugs-by-priority**
```json
[
    {
        "priority": "HIGH",
        "count": 18
    },
    {
        "priority": "CRITICAL",
        "count": 12
    }
]
```

## Prerequisites

- Java 21 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

## Setup Instructions

### 1. Database Setup

Create PostgreSQL database:
```sql
CREATE DATABASE buganalytics;
```

### 2. Clone and Configure

```bash
git clone https://github.com/soundar-19/SpringBootTraining
cd PROJECT2/day10/buganalyticsapi
```

### 3. Database Configuration

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/buganalytics
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Install Dependencies

```bash
mvn clean install
```

### 5. Load Sample Data
```sql
INSERT INTO projects (name) VALUES
('E-Commerce Platform'),
('Mobile Banking App'), 
('Customer Portal'),
('Inventory System'),
('Payment Gateway'),
('CRM System'),
('Analytics Dashboard'),
('Content Management System'),
('Mobile Wallet'),
('Logistics Platform');

-- Insert sample bugs
INSERT INTO bugs (title, status, priority, project_id) VALUES
('Login page not responsive', 'OPEN', 'HIGH', 1),
('Payment processing timeout', 'IN_PROGRESS', 'CRITICAL', 1),
('Invalid order total calculation', 'RESOLVED', 'HIGH', 1),
('Product images not loading', 'OPEN', 'MEDIUM', 1),
('Checkout process error', 'IN_PROGRESS', 'HIGH', 1),

('App crashes on startup', 'OPEN', 'CRITICAL', 2),
('Transaction history missing', 'IN_PROGRESS', 'HIGH', 2),
('Biometric auth failed', 'RESOLVED', 'HIGH', 2),
('Balance not updating', 'OPEN', 'CRITICAL', 2),
('OTP not received', 'IN_PROGRESS', 'HIGH', 2);
```

### 6. Run Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Sample Data

The application includes sample data with:
- **10 Projects**: E-Commerce Platform, Mobile Banking App, Customer Portal, etc.
- **50 Bugs**: 5 bugs per project with various statuses and priorities

### Bug Statuses
- `OPEN` - Newly reported bugs
- `IN_PROGRESS` - Bugs being worked on
- `RESOLVED` - Fixed bugs pending verification
- `CLOSED` - Verified and closed bugs

### Bug Priorities
- `LOW` - Minor issues
- `MEDIUM` - Standard priority
- `HIGH` - Important issues
- `CRITICAL` - Urgent fixes required

## Testing the API

### Using cURL

```bash
# Get bugs by project
curl http://localhost:8080/api/analytics/bugs-by-project

# Get bugs by status
curl http://localhost:8080/api/analytics/bugs-by-status

# Get bugs by priority
curl http://localhost:8080/api/analytics/bugs-by-priority
```

### Using Browser

Navigate to:
- http://localhost:8080/api/analytics/bugs-by-project
- http://localhost:8080/api/analytics/bugs-by-status
- http://localhost:8080/api/analytics/bugs-by-priority

## Project Structure

```
src/
├── main/
│   ├── java/com/buganalyticsapi/buganalyticsapi/
│   │   ├── controller/
│   │   │   └── AnalyticsController.java
│   │   ├── domain/
│   │   │   ├── Bug.java
│   │   │   └── Project.java
│   │   ├── dto/
│   │   │   ├── ProjectBugCountDTO.java
│   │   │   ├── StatusCountDTO.java
│   │   │   └── PriorityCountDTO.java
│   │   ├── repository/
│   │   │   └── BugRepository.java
│   │   ├── service/
│   │   │   └── AnalyticsService.java
│   │   └── BuganalyticsapiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
```

## Configuration

### Application Properties

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/buganalytics
spring.datasource.username=YYY
spring.datasource.password=XXX

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Logging
logging.level.org.springframework=WARN
```

## Health Check

Spring Boot Actuator provides health endpoints:
- http://localhost:8080/actuator/health

## Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Verify PostgreSQL is running
   - Check database credentials in `application.properties`
   - Ensure database `buganalytics` exists

2. **Port Already in Use**
   - Change port in `application.properties`: `server.port=8081`

3. **Sample Data Not Loading**
   - Ensure tables are created first (run application once)
   - Check SQL file path and permissions
   - Verify database connection

## Development

### Adding New Analytics

1. Create new DTO in `dto` package
2. Add query method in `BugRepository`
3. Add service method in `AnalyticsService`
4. Add controller endpoint in `AnalyticsController`

### Running Tests

```bash
mvn test
```

## License

This project is for educational purposes.