# 🎟️ Ticket Issue Java App

A comprehensive **Java-based Ticket Management Application** designed with a progressive, modular structure. This project demonstrates core **Object-Oriented Programming (OOP)** principles in each phase and is structured for future enhancement with technologies like **Spring Boot**, **REST APIs**, and **databases**.

Initially developed as a series of hands-on OOP exercises, it now forms a complete mini-project showcasing real-world design, scalability, and clean code practices.

---

## 📆 Phased Project Structure

```text
Ticket-Issue-Java-App/
├── day1proj1/                # Phase 1: Basic OOP, single service
├── day2proj2phase1/         # Phase 2.1: Helpdesk with user-ticket mapping
├── day2proj1/               # Phase 2.2: TicketService and user association
├── README.md                # General project overview (this file)
└── ...                      # Future enhancements
```

Each subfolder contains a separate Java module/project for a specific learning goal or system component.

---

## 🚀 Features Across Phases

* Ticket creation and user assignment
* Console-based interaction for displaying details
* Encapsulation, constructors, and class interactions
* Service layer abstraction for handling logic
* Maven-based structure and builds
* Future-ready for REST APIs and Spring Boot integration

---

## 🛠️ Technologies Used

* Java 21 
* Maven
* JUnit (where applicable)
* Spring Boot (planned)
* (Planned) H2 / MySQL

---

## 📅 Future Enhancements

* [ ] RESTful API layer using Spring Boot
* [ ] Integration with relational database (H2 or MySQL)
* [ ] Web-based frontend
* [ ] Exception handling and input validation
* [ ] Dockerization
* [ ] Authentication with JWT / OAuth

---

## 🖊️ How to Run (Maven)

To build and run any module (e.g., `day2proj2phase1`):

```bash
cd day2proj2phase1
mvn clean install
mvn exec:java -Dexec.mainClass="com.day2proj2phase1.Main"
```

To run tests:

```bash
mvn test
```

---

## 👨‍💻 Author

- **Soundar Raja B**
- GitHub: [soundar-19](https://github.com/soundar-19)

---

> This app is a work in progress, evolving through hands-on implementation and clean coding practices. Ideal for learning and demonstrating Java OOP and backend service design.
