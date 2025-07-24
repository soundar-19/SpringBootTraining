# 📝 User Registration System – Day 4 Project 1 (Phase 1)

## 📋 Project Description

This project is a simple **User Registration System** built using Java and Object-Oriented Programming principles. It demonstrates exception handling by validating user age during registration. If a user is under 18, a custom exception is thrown and handled gracefully.

The application demonstrates:
- Encapsulation and constructor logic in the `User` model
- Service layer for business logic (`UserService`)
- Custom exception handling (`InvalidAgeException`)
- Clean package structure (`models`, `services`, `exceptions`, `main`)
- Console-based feedback for successful and failed registrations

The main application creates multiple users, attempts to register each, and displays output for all registration attempts, including error messages for invalid ages.

---

## 🗂️ Project Structure

```
day4proj1phase1/
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
│           └── exceptions/
│               └── InvalidAgeException.java
│   └── test/
│       └── java/
│           └── com/
│               └── day4proj1phase1/
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

- **main.Main**  
  Main class. Creates users, attempts registration, and handles exceptions for each user.

---

## 📝 Example Output

```
User Sachin has been registered successfully
Registration failed. User must be atleast 18 years old
User Dhoni has been registered successfully
User Ashwin has been registered successfully
```

---

## 👤 Author

**Soundar Raja