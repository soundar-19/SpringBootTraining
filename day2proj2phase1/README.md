# 💾 Ticketing System – Day 2 Project 2 (Phase 1)

## 🔹 Project Name: `day2proj2phase1`

## 🌟 Objective

This project is a console-based **Helpdesk Ticketing System**, developed as part of Day 2’s focus on Object-Oriented Programming (OOP) principles. It aims to demonstrate encapsulation, constructor logic, and class interactions using Java.

---

## ✅ Features Implemented

### 1. `User` Class

* Fields:

  * `userId` (int)
  * `userName` (String)
  * `email` (String)
* Encapsulated using private access modifiers and public getters.
* Includes a `displayDetails()` method to print user info.
* Implemented using a parameterized constructor.

### 2. `Ticket` Class

* Fields:

  * `ticketId` (int)
  * `title` (String)
  * `description` (String)
  * `status` (String)
  * `user` (reference to a `User` object)
* Fully encapsulated with private fields and public getters.
* Includes a `displayDetails()` method that also shows the associated user's details.
* Uses a parameterized constructor.

### 3. `Main` Class

* Created two `User` objects.
* Created three `Ticket` objects, each linked to a user.
* Used `displayDetails()` methods to print ticket and user details to the console.

---

## ⚙️ Technical Stack

* **Language:** Java
* **Java Version:** 21
* **Build Tool:** Maven (`maven-archetype-quickstart`)
* **Structure:**

  ```
  src/main/java/com/day2proj2phase1/
    ├── User.java
    ├── Ticket.java
    └── Main.java
  ```

---

## 📤 How to Run

1. Clone the repository:

   ```bash
   git clone <your-repo-link>
   ```
2. Navigate to the project folder:

   ```bash
   cd day2proj2phase1
   ```
3. Build the project:

   ```bash
   mvn clean install
   ```
4. Run the application:

   ```bash
   mvn exec:java -Dexec.mainClass="com.day2proj2phase1.Main"
   ```

---

## 🖊️ Summary

This project demonstrates:

* Basic OOP principles in Java
* Class composition and interaction
* Console-based data display using `displayDetails()` methods

This concludes **Phase 1** of the Helpdesk Ticketing System. In **Phase 2**, the system will be extended with a menu-driven interface for adding, listing, and updating tickets.

---

## 🚀 Author

 **Soundar Raja B**
