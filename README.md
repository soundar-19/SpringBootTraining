# 🎟️ Ticket Issue Java App

A comprehensive Java-based ticket management application designed with modular phases. It follows object-oriented principles and is built to scale with Spring Boot integration. Initially started as **Phase 1 of Project 1**, the project is structured for progressive enhancement through additional phases and features.

---

## 📦 Project Structure

```text
project-root/
├── .gitignore
├── .vscode/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/com/ticketapp/
│   │       ├── App.java
│   │       ├── Ticket.java
│   │       ├── User.java
│   │       ├── TicketService.java
│   │       └── UserService.java
│   └── test/
│       └── java/com/ticketapp/
│           └── AppTest.java
```

---

## 🚀 Key Features

* Create and manage tickets
* Assign tickets to users
* CRUD operations for both tickets and users
* RESTful API endpoints using Spring Boot (planned)
* In-memory data handling (can be replaced with databases later)
* Modular architecture for future expansion

---

## 🛠️ Technologies Used

* Java 21
* Spring Boot
* Maven
* JUnit 5
* (Planned) H2 or MySQL for persistence

---

## 📄 Class Overview

### `App.java`

* Main launcher for the application.

### `Ticket.java`

* Ticket entity: ID, title, status, and related user.

### `User.java`

* User entity: ID, name, and ticket ownership.

### `TicketService.java`

* Handles business logic for ticket operations.

### `UserService.java`

* Handles business logic for user operations.

* REST API controllers (planned for future phase).

---

## 🧪 Running the Application

To build and run:

```bash
mvn clean install
java -cp target/ticket-app-1.0-SNAPSHOT.jar com.ticketapp.App
```

To run tests:

```bash
mvn test
```

---

## 🧱 Planned Enhancements

* Add Spring Boot REST controllers
* Integrate with H2/MySQL database
* Add exception handling and validations
* Create a web-based frontend interface
* Dockerize the application
* Add authentication (e.g., JWT)

---

## 👨‍💻 Author

* Soundar Raja B
* GitHub: [soundar-19](https://github.com/soundar-19)
