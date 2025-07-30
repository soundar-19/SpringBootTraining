# 👨‍💼 Basic Java Application - Day 7 V2 Project 1

Welcome to the **Basic Java App**! This project prints a simple "Hello World!" message to the console, showcasing how to structure and run a minimal Java application using Maven.

---

## 📚 Features

* ✅ Minimal Java application setup
* 📊 Prints a message to the console
* 🧲 Includes basic unit testing with JUnit
* 📈 Good for Java/Maven beginners

---

## 🗂️ Project Structure

```
day7v2project1/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── day7v2proj1/
│   │               ├── App.java                               # Entry point
│   │               └── helloworld/
│   │                   ├── SpringBootHelloWorldApplication.java
│   │                   └── controller/
│   │                       └── HelloController.java
│   └── test/
│       └── java/
│           └── com/
│               └── day7v2proj1/
│                   └── AppTest.java                          # Basic test class
```

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+
* Maven 3.6+
* IDE or terminal to run the application

---

### ▶️ Run the App

1. **Build the project**

```bash
mvn clean install
```

2. **Run the application**

```bash
mvn exec:java -Dexec.mainClass="com.day7v2proj1.App"
```


## 🧪 Running Tests

To execute tests:

```bash
mvn test
```

---

## 🙋‍♂️ Author

Developed by **Soundar Raja B**
Ideal for learners taking their first step into Java development!

---

## 📌 License

This project is free and open-source for learning and demonstration purposes.
