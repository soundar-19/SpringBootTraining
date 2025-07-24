# 📝 User Registration System – Day 4 Project 1 (Phase 2)

## 📋 Project Description

This project is a console-based **User Registration System** built in Java, demonstrating OOP principles and robust exception handling. Users are registered only if their age is 18 or above; otherwise, a custom exception is thrown and the error is logged to a file. The application uses a service layer for business logic and a utility logger for error tracking.

**Key Concepts:**
- Encapsulation in the `User` model
- Service layer for registration logic (`UserService`)
- Custom exception handling (`InvalidAgeException`)
- Utility class for error logging (`Logger`)
- Interactive console input and menu-driven flow

---

## 🗂️ Project Structure

```
day4proj1phase2/
├── README.md
├── pom.xml
├── .gitignore
├── src/
│   └── main/
│       └── java/
│           ├── main/
│           │   └── Main.java
│           ├── models/
│           │   └── User.java
│           ├── services/
│           │   └── UserService.java
│           ├── exceptions/
│           │   └── InvalidAgeException.java
│           └── utils/
│               └── Logger.java
│   └── test/
│       └── java/
│           └── com/
│               └── day4proj1phase2/
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
  Encapsulates user properties (`userName`, `age`) and provides getters.

- **services.UserService**  
  Contains business logic for user registration. Throws `InvalidAgeException` if age < 18.

- **exceptions.InvalidAgeException**  
  Custom exception for invalid age during registration.

- **utils.Logger**  
  Logs registration errors to a file (`error.log`) with timestamp and user details.

- **main.Main**  
  Main class. Handles user input, registration, and error logging via menu-driven console.

---

## 📝 Example Output

```
Welcome, Choose the option you want to perform:
1. Register User
2. Exit
Enter username: Sachin
Enter age: 17
Registration failed. User must be atleast 18 years old
Welcome, Choose the option you want to perform:
1. Register User
2. Exit
Enter username: Virat
Enter age: 25
User Virat has been registered successfully
```
## error.log (sample):
```
2025-07-24T13:02:50.650029500 User: klrahul, Age: 2, Error: Registration failed. User must be atleast 18 years old
2025-07-24T13:04:27.684596800 User: watson, Age: 17, Error: Registration failed. User must be atleast 18 years old

```
---

## 👤 Author

- **Soundar Raja B**
