# 📅 Ticket Management App – Day 1 Project 1 (Phase 1)

## 🔹 Project Name: `day1proj1phase1`

## 🚀 Objective

This project is a beginner-level **console-based Ticket Management App**, designed to implement basic **Object-Oriented Programming (OOP)** concepts such as classes, constructors, encapsulation, and method usage in Java.

---

## ✅ Features Implemented

### 1. `User` Class

* Fields:

  * `id` (int)
  * `name` (String)
  * `email` (String)
* Fully encapsulated with private fields and public getters.
* Includes a `displayUser()` method to show user information.
* Uses a parameterized constructor to initialize the object.

### 2. `Ticket` Class

* Fields:

  * `ticketId` (int)
  * `title` (String)
  * `status` (String, default = "Open")
* Encapsulated with private fields and public getters.
* Methods:

  * `closeTicket()` – updates status to "Closed"
  * `displayTicket()` – displays ticket information
* Uses a constructor to set `ticketId` and `title` with default `status`.

### 3. `App` Class

* Acts as the entry point of the application.
* Demonstrates:

  * Creation of a `User` object and displaying details
  * Creation of a `Ticket` object and displaying its status
  * Closing a ticket and redisplaying the updated status

---

## ⚙️ Technical Stack

* **Language:** Java
* **Java Version:** 21
* **Build Tool:** Maven (`maven-archetype-quickstart`)
* **Structure:**

  ```
  src/main/java/com/day1proj1phase1/
    ├── User.java
    ├── Ticket.java
    └── App.java
  ```

---

## 📄 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/soundar-19/SpringBootTraining.git
   ```
2. Navigate to the project folder:

   ```bash
   cd day1proj1phase1
   ```
3. Build the project:

   ```bash
   mvn clean install
   ```
4. Run the application:

   ```bash
   mvn exec:java -Dexec.mainClass="com.day1proj1phase1.App"
   ```

---

## 🖊️ Summary

This project demonstrates:

* Class definition and instantiation
* Use of constructors and methods
* Encapsulation through access modifiers
* Displaying object data in the console

This concludes **Phase 1** of the Ticket Management App. Future phases will include user-ticket associations, services, and menu-driven interactivity.

## 🚀 Author
- **Soundar Raja B**
