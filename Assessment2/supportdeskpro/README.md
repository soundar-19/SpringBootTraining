# 💠 SupportDeskPro

**SupportDeskPro** is a Java-based support desk management system built with **Spring Boot**. It offers:

* A **RESTful API** for external integrations
* A **console-based interactive application** for managing tickets and users via terminal

---

## 🚀 Features

* User and ticket management (create, assign, close, list)
* REST API endpoints for all core operations
* Console-based menu interface for interactive use
* Modular structure with clear separation of concerns
* Configured for **PostgreSQL** database

---

## 🧰 Technologies Used

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **Jakarta Persistence API**
* **PostgreSQL**
* **Maven**

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       ├── supportdeskpro/
│   │       │   ├── SupportdeskproApplication.java
│   │       │   ├── controller/
│   │       │   │   ├── TicketController.java
│   │       │   │   └── UserController.java
│   │       │   ├── domain/
│   │       │   │   ├── Ticket.java
│   │       │   │   └── User.java
│   │       │   ├── repository/
│   │       │   │   ├── TicketRepository.java
│   │       │   │   └── UserRepository.java
│   │       │   └── service/
│   │       │       ├── TicketService.java
│   │       │       └── UserService.java
│   │       └── supportdesk_console/
│   │           ├── Main.java
│   │           ├── controller/
│   │           │   ├── TicketController.java
│   │           │   └── UserController.java
│   │           ├── domain/
│   │           │   ├── Ticket.java
│   │           │   └── User.java
│   │           ├── repository/
│   │           │   ├── TicketRepository.java
│   │           │   └── UserRepository.java
│   │           ├── repository/impl/
│   │           │   ├── TicketRepositoryImpl.java
│   │           │   └── UserRepositoryImpl.java
│   │           ├── service/
│   │           │   ├── TicketService.java
│   │           │   └── UserService.java
│   │           ├── exception/
│   │           │   ├── UserNotFoundException.java
│   │           │   ├── TicketNotFoundException.java
│   │           │   └── InvalidInputException.java
│   │           └── util/
│   │               └── InputValidator.java
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── test/
    └── java/
        └── com/
            └── supportdeskpro/
                └── SupportdeskproApplicationTests.java
```

---

## 📊 How to Build and Run

### ✅ Prerequisites

* Java 17+
* Maven
* PostgreSQL database setup

---

### 🔨 Build the Project

```bash
mvn clean install
```

---

### ▶️ Run the REST API Application

```bash
mvn spring-boot:run
```

API available at:

```
http://localhost:8080/api/
```

---

### 🖥️ Run the Console Application

Run the following class:

```
com.supportdesk_console.Main.java
```

This starts a terminal-based menu interface for user and ticket management.

---

## 📌 Configuration Notes

* PostgreSQL configuration is done via `application.properties`. Example:

  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/supportdesk
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  ```

* You can switch to another DB like MySQL by changing the above config and dependencies in `pom.xml`.

---

## 📢 Contributing

Contributions and feedback are welcome. Please fork the repository and submit a pull request.

---

