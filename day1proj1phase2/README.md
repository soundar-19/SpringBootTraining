# 🎟️ Ticket Management System – Day 1 (Project Phase 2)

## 🌟 Objective

This project builds upon the previous phase by introducing more structured and object-oriented programming practices. The goals for Day 1 include:

* Introducing a `TicketService` class to manage all ticket-related operations.
* Handling a list of tickets instead of a single static ticket.
* Creating a new `User` class to manage user-related data.
* Establishing relationships between `Ticket` and `User` classes.
* Practicing **OOP principles** such as encapsulation, separation of concerns, and modular design.
* Writing **clean, maintainable, and real-world**-like application structure.

---

## 🛠️ Key Features

### ✅ `TicketService` Class

* Maintains a list of `Ticket` objects.
* Provides methods to:

  * Add a new ticket.
  * Display all tickets and their associated user details.

### ✅ `User` Class

* Attributes:

  * `userId`
  * `userName`
  * `email`
* Includes:

  * Constructor
  * Getter methods
  * `displayUser()` method

### ✅ Ticket Enhancements

* The `Ticket` class is modified to include a `User` object as one of its properties.
* While displaying a ticket, the corresponding user’s details are also shown.

### ✅ `App` Class

* Responsible for:

  * Creating multiple `Ticket` and `User` objects.
  * Linking each ticket with a corresponding user.
  * Storing and managing tickets using `TicketService`.
  * Displaying all ticket and user details clearly in the output.

---

## 📂 Project Structure

```
day1proj1phase2/
└── src/
    └── main/
        └── java/
            └── com/
                └── day1proj1phase2/
                    ├── Ticket.java
                    ├── User.java
                    ├── TicketService.java
                    └── App.java
```

---

## ✅ Guidelines

* Make sure the project builds successfully using Maven:

  ```bash
  mvn clean install
  ```

* Run the project and verify the following output:

  * All **ticket details**.
  * All **associated user details** for each ticket.

---

