# 🚄 Ticket Booking System – Day 4 Project 2

## 📋 Project Description

This project is a console-based **Ticket Booking System** built in Java, demonstrating Object-Oriented Programming, custom exception handling, and error logging. Users can book tickets by providing their details; the system validates input, handles errors gracefully, and logs issues for audit.

**Key Concepts:**
- Encapsulation and inheritance (`Ticket` extends `User`)
- Service layer for business logic (`TicketService`)
- Custom exceptions for validation (`InvalidAgeException`, `EmptyFieldException`)
- Utility class for error logging (`Logger`)
- Interactive menu-driven console application

---

## 🗂️ Project Structure

```
day4proj2/
├── README.md
├── pom.xml
├── .gitignore
├── src/
│   └── main/
│       └── java/
│           ├── main/
│           │   └── Main.java
│           ├── models/
│           │   ├── Ticket.java
│           │   └── User.java
│           ├── services/
│           │   └── TicketService.java
│           ├── exceptions/
│           │   ├── InvalidAgeException.java
│           │   └── EmptyFieldException.java
│           └── utils/
│               └── Logger.java
│   └── test/
│       └── java/
│           └── com/
│               └── day4proj2/
│                   └── AppTest.java
```

---

## 🚀 How to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```
2. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```
   Or run `Main.java` directly from your IDE.

---

## 🧩 Key Classes

- **models.User**  
  Encapsulates user properties (`userName`, `age`).

- **models.Ticket**  
  Inherits from `User`, adds `source` and `destination`, and provides a method to display ticket info.

- **services.TicketService**  
  Handles ticket booking and listing. Validates input and throws custom exceptions for invalid data.

- **exceptions.InvalidAgeException & EmptyFieldException**  
  Custom exceptions for age and empty field validation.

- **utils.Logger**  
  Logs errors to `error.log` with timestamp and ticket details.

- **main.Main**  
  Main class. Handles user input, ticket booking, error handling, and menu navigation.

---

## 📝 Example Output

```
Choose an option:
1. Add Ticket
2. List Tickets
3. Exit
1
Enter username: John
Enter age: 17
Enter source: Chennai
Enter destination: Bangalore
Booking failed. User must be at least 18 years old
(Logged to error.log)

Choose an option:
1. Add Ticket
2. List Tickets
3. Exit
1
Enter username: Alice
Enter age: 25
Enter source: Mumbai
Enter destination: Pune
Ticket booked succesfully for user: Alice

Choose an option:
1. Add Ticket
2. List Tickets
3. Exit
2
Ticket Information
UserName: Alice
Age: 25
Source: Mumbai
Destination: Pune
```

---

## 👤 Author

- **Soundar Raja B**
- GitHub Repo Link: [day4proj2](https://github.com/soundar-19/SpringBootTraining/tree/main/day4proj2)
