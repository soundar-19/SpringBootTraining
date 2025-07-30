# 🎉 Spring Boot Greeting Application - Day 7 V2 Project 1 Phase 2

Welcome to the **Spring Boot Greeting App**! This project provides a set of greeting-related REST APIs built using **Spring Boot**. It demonstrates the use of multiple controllers to organize endpoints cleanly and effectively.

---

## 📚 Features

* ✅ Multiple REST endpoints using Spring Boot controllers
* 🛋 Organized project structure for scalability
* 🧲 Includes basic test class for validation
* 💡 Ideal for learning multi-controller architecture in Spring

---

## 🗂️ Project Structure

```
day7v2proj1phase2/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── day7v2project1phase2/
│   │   │           ├── SpringBootGreetingApplication.java      # Main class
│   │   │           └── controller/
│   │   │               ├── GreetController.java               # /greet endpoint
│   │   │               ├── HelloController.java               # /hello endpoint
│   │   │               └── InfoController.java                # /info endpoint
│   └── test/
│       └── java/
│           └── com/
│               └── day7v2project1phase2/
│                   └── AppTest.java                          # Basic unit test
```

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+
* Maven 3.6+
* Your favorite IDE (IntelliJ, VS Code, etc.)

---

### ▶️ Run the App

1. **Clone the repository**

```bash
git clone https://github.com/your-username/day7v2proj1phase2.git
cd day7v2proj1phase2
```

2. **Build the project**

```bash
mvn clean install
```

3. **Run the application**

```bash
mvn spring-boot:run
```

4. **Access the endpoints** (Default port: `8080`)

* `http://localhost:8080/greet`
* `http://localhost:8080/hello`
* `http://localhost:8080/info`

---

## 🧪 Running Tests

To run tests:

```bash
mvn test
```

---

## 🙋‍♂️ Author

Created by **Soundar Raja B**
Feel free to connect or fork the project for your own enhancements.

---

## 📌 License

Free to use for educational and demo purposes.
