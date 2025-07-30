# 🌦️ Spring Boot Weather Application - Day 7 Project 2

Welcome to the **Spring Boot Weather App**! This simple RESTful service gives you weather updates via HTTP requests. It’s built using **Spring Boot** and is perfect for learning how to create web APIs with Java.

---

## 📚 Features

* ✅ RESTful API using Spring Boot
* 🌐 Endpoint for retrieving weather information
* 🧲 Includes basic unit testing
* 🚠 Follows clean and modular project structure

---

## 🗂️ Project Structure

```
day7project2/
├── .gitignore
├── pom.xml                   # Maven build file
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── day7project2/
│   │               ├── WeatherApplication.java         # Main entry point
│   │               └── controller/
│   │                   └── WeatherController.java      # REST controller
│   └── test/
│       └── java/
│           └── com/
│               └── day7project2/
│                   └── AppTest.java                    # Sample test class
```

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+ (or your project's target version)
* Maven 3.6+
* IDE like IntelliJ IDEA or VS Code

---

### ▶️ Run the App

1. **Clone the repository**

```bash
git clone https://github.com/your-username/day7project2.git
cd day7project2
```

2. **Build the project**

```bash
mvn clean install
```

3. **Start the application**

```bash
mvn spring-boot:run
```

4. **Access the Weather API**

Once running, go to:
`http://localhost:8080/weather/today`
(or whatever your endpoint is)

---

## 🧪 Running Tests

To run unit tests:

```bash
mvn test
```

---

## 🛌 Example Endpoint (Sample)

```http
GET /weather/today
```

---

## 🙋‍♂️ Author

Created with 💻 by **Soundar Raja B**

---

## 📌 License

This project is open-source and free to use for educational purposes.
