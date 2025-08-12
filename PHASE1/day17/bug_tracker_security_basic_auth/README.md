# Bug Tracker with Basic Authentication & Pagination

A Spring Boot REST API application for managing bugs with basic authentication security and advanced pagination/sorting features.

## Features

- **Bug Management**: Create, read, update, and delete bug reports
- **Filtering**: Filter bugs by status, assignee, or project
- **Advanced Pagination**: Multiple sort fields, search with paging, metadata-only responses
- **Basic Authentication**: Secure API endpoints with username/password
- **In-Memory Database**: H2 database for development and testing
- **RESTful API**: Clean REST endpoints for all operations

## Technology Stack

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security** (Basic Authentication)
- **Spring Data JPA**
- **H2 Database** (In-memory)
- **Maven** (Build tool)
- **Lombok** (Code generation)

## Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use included Maven wrapper)

## Quick Start

### 1. Clone and Build

```bash
git clone <repository-url>
cd bug_tracker_security_basic_auth
./mvnw clean install
```

### 2. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Authentication

All API endpoints require basic authentication:
- **Username**: `admin`
- **Password**: `password`

## API Endpoints

### Base URL: `/api/bugs`

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| GET | `/api/bugs` | Get all bugs with pagination | `page`, `size`, `sort` (query params) |
| GET | `/api/bugs/search` | Search with filters and pagination | `status`, `assignee`, `project`, `page`, `size`, `sort` |
| GET | `/api/bugs/slice` | Pagination without total count | `page`, `size`, `sort` |
| GET | `/api/bugs/metadata` | Only pagination metadata | `status`, `assignee`, `project`, `page`, `size` |
| GET | `/api/bugs/filter` | Filter bugs (no pagination) | `status`, `assignee`, `project` |
| GET | `/api/bugs/id/{id}` | Get bug by ID | `id` (path variable) |
| GET | `/api/bugs/status/{status}` | Get bugs by status | `status` (path variable) |
| GET | `/api/bugs/assignee/{assignee}` | Get bugs by assignee | `assignee` (path variable) |
| GET | `/api/bugs/project/{project}` | Get bugs by project | `project` (path variable) |

## Pagination Features

### Multiple Sort Fields
```bash
GET /api/bugs?page=1&size=5&sort=status,asc&sort=title,desc
```

### Search with Paging
```bash
GET /api/bugs/search?status=Open&page=1&size=3
```

### Metadata Only
```bash
GET /api/bugs/metadata?status=Open&page=1&size=10
```

### Example Requests

#### Get all bugs with pagination
```bash
curl -u admin:password "http://localhost:8080/api/bugs?page=1&size=5"
```

#### Search with multiple sort fields
```bash
curl -u admin:password "http://localhost:8080/api/bugs?page=1&size=5&sort=status,asc&sort=title,desc"
```

#### Filter and paginate
```bash
curl -u admin:password "http://localhost:8080/api/bugs/search?status=Open&page=1&size=3"
```

#### Get only metadata
```bash
curl -u admin:password "http://localhost:8080/api/bugs/metadata?status=Open&page=1&size=10"
```

## Configuration

- Pages start at 1 (human-friendly)
- Default page size: 10
- Default sort: title
- H2 database with console at `/h2-console`

### Application Properties

Key pagination configuration in `application.properties`:

```properties
# Pagination Configuration
spring.data.web.pageable.default-page-size=10
spring.data.web.pageable.page-parameter=page
spring.data.web.pageable.size-parameter=size
spring.data.web.pageable.one-indexed-parameters=true

# Security
spring.security.user.name=admin
spring.security.user.password=password

# Database
spring.datasource.url=jdbc:h2:mem:bugtracker
spring.h2.console.enabled=true
```

## Example Responses

### Paginated Response
```json
{
  "content": [...],
  "totalElements": 50,
  "totalPages": 5,
  "number": 0,
  "size": 10,
  "hasNext": true,
  "hasPrevious": false
}
```

### Metadata Response
```json
{
  "totalElements": 50,
  "totalPages": 5,
  "currentPage": 0,
  "pageSize": 10,
  "hasNext": true,
  "hasPrevious": false
}
```

## Sample Data

The application loads sample data on startup:

- 10 sample bugs with different statuses (Open, In Progress, Closed)
- Assigned to various team members (John, Jane, Bob, Alice)
- Distributed across projects (Project A, Project B, Project C)

## Database Access

### H2 Console (Development)

Access the H2 database console at: `http://localhost:8080/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:bugtracker`
- Username: `sa`
- Password: (leave empty)

## Project Structure

```
src/
├── main/
│   ├── java/com/ex/bug_tracker_security_basic_auth/
│   │   ├── controller/          # REST controllers
│   │   ├── service/             # Business logic
│   │   ├── repository/          # Data access layer
│   │   ├── entity/              # JPA entities
│   │   ├── dto/                 # Data transfer objects
│   │   └── BugTrackerSecurityBasicAuthApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/                    # Test classes
```

## Development

### Running in Development Mode

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building for Production

```bash
./mvnw clean package
java -jar target/bug_tracker_security_basic_auth-0.0.1-SNAPSHOT.jar
```

## Testing

Run tests with:

```bash
./mvnw test
```

## API Screenshots

![GET : get bugs by id](image.png)
![GET : Get bugs by assignee](image-1.png)
![GET : Get bugs by status](image-2.png)
![GET : Get bugs by project](image-4.png)
![GET : Get bugs by filtering](image-3.png)
![GET : Get bugs by paging](image-5.png)
![GET : Filtering](image-6.png)
![GET : Metadata](image-7.png)
![GET : Slicing](image-8.png)
![GET : Search & Sort](image-9.png)

## Troubleshooting

### Common Issues

1. **Port Already in Use**: Change `server.port` in `application.properties`
2. **Authentication Failed**: Verify username/password (`admin`/`password`)
3. **Database Connection**: Check H2 console configuration

### Logs

Enable debug logging by adding to `application.properties`:

```properties
logging.level.com.ex.bug_tracker_security_basic_auth=DEBUG
logging.level.org.springframework.security=DEBUG
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is for educational purposes as part of Spring Boot training.