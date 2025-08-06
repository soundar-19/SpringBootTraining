<div align="center">

# 🐛 IssueTrackerORM

<img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=30&duration=3000&pause=1000&color=36BCF7&center=true&vCenter=true&width=600&lines=Bug+Tracking+Made+Simple;Spring+Boot+%2B+PostgreSQL;Enterprise+Ready+Solution" alt="Typing SVG" />

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
> Production-grade security and scalability

### 🎯 **Developer Friendly**
> Clean architecture with comprehensive APIs

### 🛡️ **Robust Error Handling**
> Global exception management with custom DTOs

</td>
<td width="50%">

### 📊 **Real-time Tracking**
> Monitor bugs, projects, and users seamlessly

### 🔄 **Full CRUD Operations**
> Complete lifecycle management for all entities

### 🎨 **RESTful Design**
> Beautiful, intuitive API endpoints

### 📦 **Clean Data Layer**
> DTOs and mappers for secure data transfer

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
    
    E[🐛 Bug Entity] --> C
    F[📁 Project Entity] --> C
    G[👤 User Entity] --> C
    
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
<summary>🐛 <strong>Bug Management System</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 📝 **Create Bugs** | Auto-timestamped bug creation | ✅ |
| 🔍 **Smart Filtering** | Filter by status, priority, project, assignee | ✅ |
| 🎯 **Unresolved Tracking** | Find pending bugs by user | ✅ |
| 📊 **Analytics** | Count bugs by project | ✅ |
| 🔄 **Status Updates** | Real-time status management | ✅ |
| 🗑️ **Cleanup** | Safe bug deletion | ✅ |

</details>

<details>
<summary>📁 <strong>Project Management Hub</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 🏗️ **Project CRUD** | Complete project lifecycle | ✅ |
| 🔎 **Smart Search** | Find projects by name | ✅ |
| 📈 **Statistics** | Project counting & analytics | ✅ |
| 🔗 **Bug Relations** | Projects with assigned bugs | ✅ |
| ✏️ **Name Updates** | Dynamic project renaming | ✅ |

</details>

<details>
<summary>👥 <strong>User Management Portal</strong></summary>
<br>

| Feature | Description | Status |
|---------|-------------|--------|
| 👤 **User CRUD** | Complete user management | ✅ |
| 🏷️ **Role-based Filtering** | Filter users by roles | ✅ |
| 📊 **Role Analytics** | Count users by role | ✅ |
| 🎯 **Assignment Tracking** | Users with bug assignments | ✅ |
| 🔄 **Profile Updates** | Dynamic user information updates | ✅ |

</details>

---

## 🏛️ **Project Architecture**



<pre>
🏗️ IssueTrackerORM/
┣ 📂 src/
┃ ┣ 📂 main/
┃ ┃ ┣ ☕ java/com/example/IssueTrackerORM/
┃ ┃ ┃ ┣ 🎮 controller/          ← REST API Magic
┃ ┃ ┃ ┃ ┣ 🐛 BugController.java
┃ ┃ ┃ ┃ ┣ 📁 ProjectController.java
┃ ┃ ┃ ┃ ┗ 👤 UserController.java
┃ ┃ ┃ ┣ 🏗️ domain/             ← Entity Models
┃ ┃ ┃ ┃ ┣ 🐛 Bug.java
┃ ┃ ┃ ┃ ┣ 📁 Project.java
┃ ┃ ┃ ┃ ┗ 👤 User.java
┃ ┃ ┃ ┣ 📦 dto/                ← Data Transfer Objects
┃ ┃ ┃ ┃ ┣ 🐛 BugRequestDTO.java
┃ ┃ ┃ ┃ ┗ 🐛 BugResponseDTO.java
┃ ┃ ┃ ┣ 🛡️ exception/          ← Global Exception Handling
┃ ┃ ┃ ┃ ┣ 🌐 GlobalExceptionHandler.java
┃ ┃ ┃ ┃ ┣ 🔍 ResourceNotFoundException.java
┃ ┃ ┃ ┃ ┣ ❌ InvalidInputException.java
┃ ┃ ┃ ┃ ┣ 🔄 DuplicateResourceException.java
┃ ┃ ┃ ┃ ┗ 💾 DatabaseOperationException.java
┃ ┃ ┃ ┣ 🔄 mapper/             ← Entity-DTO Mapping
┃ ┃ ┃ ┃ ┗ 🐛 BugMapper.java
┃ ┃ ┃ ┣ 🗄️ repository/         ← Data Access Layer
┃ ┃ ┃ ┃ ┣ 🐛 BugRepository.java
┃ ┃ ┃ ┃ ┣ 📁 ProjectRepository.java
┃ ┃ ┃ ┃ ┗ 👤 UserRepository.java
┃ ┃ ┃ ┣ ⚙️ service/            ← Business Logic
┃ ┃ ┃ ┃ ┣ 🐛 BugService.java & BugServiceImpl.java
┃ ┃ ┃ ┃ ┣ 📁 ProjectService.java & ProjectServiceImpl.java
┃ ┃ ┃ ┃ ┗ 👤 UserService.java & UserServiceImpl.java
┃ ┃ ┃ ┗ 🚀 IssueTrackerOrmApplication.java
┃ ┃ ┗ 📂 resources/
┃ ┃   ┣ ⚙️ application.properties
┃ ┃   ┣ 🎨 static/
┃ ┃   ┗ 📄 templates/
┃ ┗ 🧪 test/
┗ 📦 pom.xml
</pre>



---

## 🛠️ **API Endpoints**

<div align="center">

### 🐛 **Bug Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟢 `GET` | `/api/bugs/` | Fetch all bugs | `200 OK` |
| 🟢 `GET` | `/api/bugs/{id}` | Get specific bug | `200 OK` |
| 🟡 `POST` | `/api/bugs/create` | Create new bug | `201 Created` |
| 🟢 `GET` | `/api/bugs/status/{status}` | Filter by status | `200 OK` |
| 🟢 `GET` | `/api/bugs/priority/{priority}` | Filter by priority | `200 OK` |
| 🟢 `GET` | `/api/bugs/project/{projectId}` | Bugs by project | `200 OK` |
| 🟢 `GET` | `/api/bugs/assignedTo/{userId}` | Bugs by assignee | `200 OK` |
| 🟢 `GET` | `/api/bugs/unresolved/{userId}` | Unresolved bugs | `200 OK` |
| 🟢 `GET` | `/api/bugs/count/{projectId}` | Count project bugs | `200 OK` |
| 🔵 `PUT` | `/api/bugs/{id}/{status}` | Update bug status | `200 OK` |
| 🔴 `DELETE` | `/api/bugs/{id}` | Delete bug | `200 OK` |

<div align="center">

### 📁 **Project Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟢 `GET` | `/api/projects/` | All projects | `200 OK` |
| 🟢 `GET` | `/api/projects/id/{projectId}` | Project by ID | `200 OK` |
| 🟢 `GET` | `/api/projects/name/{name}` | Project by name | `200 OK` |
| 🟡 `POST` | `/api/projects/create` | Create project | `201 Created` |
| 🟢 `GET` | `/api/projects/count` | Count all projects | `200 OK` |
| 🟢 `GET` | `/api/projects/with-bugs` | Projects with bugs | `200 OK` |
| 🔵 `PUT` | `/api/projects/{oldName}/{newName}` | Update project name | `200 OK` |
| 🔴 `DELETE` | `/api/projects/{id}` | Delete project | `200 OK` |

<div align="center">

### 👥 **User Management APIs**

</div>

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| 🟢 `GET` | `/api/users/` | All users | `200 OK` |
| 🟢 `GET` | `/api/users/{id}` | User by ID | `200 OK` |
| 🟢 `GET` | `/api/users/name/{name}` | User by name | `200 OK` |
| 🟢 `GET` | `/api/users/role/{role}` | Users by role | `200 OK` |
| 🟡 `POST` | `/api/users/create` | Create user | `201 Created` |
| 🟢 `GET` | `/api/users/count/role/{role}` | Count by role | `200 OK` |
| 🟢 `GET` | `/api/users/with-bugs` | Users with bugs | `200 OK` |
| 🔵 `PUT` | `/api/users/{id}` | Update user | `200 OK` |
| 🔴 `DELETE` | `/api/users/{id}` | Delete user | `200 OK` |

---

## ⚙️ **Configuration**

<div align="center">

### 🐘 **Database Setup**

</div>

```properties
# 🚀 Application Configuration
spring.application.name=IssueTrackerORM
server.port=8080

# 🐘 PostgreSQL Database
spring.datasource.url=jdbc:postgresql://localhost:5432/issuetracker
spring.datasource.username=XXX
spring.datasource.password=YYY

# 🔧 JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# 🎨 UI Configuration
spring.main.banner-mode=off

# 📊 Logging
logging.level.root=WARN
logging.level.com.example.practice=WARN
```

---

## 🚀 **Technology Stack**

<div align="center">

<table>
<tr>
<td align="center" width="20%">
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="60" height="60"/>
<br><strong>Spring Boot</strong>
<br><sub>3.5.4</sub>
</td>
<td align="center" width="20%">
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="60" height="60"/>
<br><strong>Java</strong>
<br><sub>21</sub>
</td>
<td align="center" width="20%">
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original.svg" width="60" height="60"/>
<br><strong>PostgreSQL</strong>
<br><sub>Latest</sub>
</td>
<td align="center" width="20%">
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" width="60" height="60"/>
<br><strong>Maven</strong>
<br><sub>Latest</sub>
</td>
<td align="center" width="20%">
<img src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" width="60" height="60"/>
<br><strong>Spring Data JPA</strong>
<br><sub>Latest</sub>
</td>
</tr>
</table>

### 🔧 **Additional Dependencies**

[![Spring Boot Actuator](https://img.shields.io/badge/Spring%20Boot%20Actuator-Monitoring-green?style=flat-square)](https://spring.io/guides/gs/actuator-service/)
[![Spring Boot DevTools](https://img.shields.io/badge/Spring%20Boot%20DevTools-Development-blue?style=flat-square)](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)

</div>

---

## 🚀 **Quick Start Guide**

<div align="center">

### 🎯 **Get Up and Running in 5 Minutes!**

</div>

```bash
# 1️⃣ Clone the repository
git clone https://github.com/soundar-19/SpringBootTraining.git
cd IssueTrackerORM

# 2️⃣ Start PostgreSQL
# Ensure PostgreSQL is running on localhost:5432

# 3️⃣ Create database
psql -U postgres -c "CREATE DATABASE issuetracker;"

# 4️⃣ Configure credentials (if needed)
# Edit src/main/resources/application.properties

# 5️⃣ Run the application
./mvnw spring-boot:run

# 🎉 That's it! Your app is running on http://localhost:8080
```

<div align="center">

### 🔗 **Quick Access Links**

[![API Documentation](https://img.shields.io/badge/📚%20API%20Docs-localhost:8080/api-blue?style=for-the-badge)](http://localhost:8080/api/)
[![Health Check](https://img.shields.io/badge/💚%20Health%20Check-localhost:8080/actuator/health-green?style=for-the-badge)](http://localhost:8080/actuator/health)

</div>

---

## 🎯 **Usage Examples**

<details>
<summary>🐛 <strong>Creating a Bug</strong></summary>

```bash
curl -X POST http://localhost:8080/api/bugs/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Login button not working",
    "description": "Users cannot login using the main login button",
    "status": "Open",
    "priority": "High",
    "project": {"id": 1}
  }'
```

</details>

<details>
<summary>📁 <strong>Creating a Project</strong></summary>

```bash
curl -X POST http://localhost:8080/api/projects/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "E-Commerce Platform"
  }'
```

</details>

<details>
<summary>👤 <strong>Creating a User</strong></summary>

```bash
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "role": "Developer"
  }'
```

</details>

---

<div align="center">

## 🌟 **Why Choose IssueTrackerORM?**

<img src="https://user-images.githubusercontent.com/74038190/212284087-bbe7e430-757e-4901-90bf-4cd2ce3e1852.gif" width="500">

### 💡 **Built with Love, Powered by Innovation**

*"Simplicity is the ultimate sophistication"* - Leonardo da Vinci

---

### 🤝 **Contributing**

Please feel free to submit a Pull Request.


---
