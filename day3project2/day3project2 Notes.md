# Issue Tracker Application

## 📋 Project Description

This project is a simple **Issue Tracker Application** built using Java and Object-Oriented Programming principles. It models two main types of issues: **Bugs** and **Tasks**, both extending an abstract `Issue` class. Each issue type implements the `Reportable` interface, allowing for polymorphic report generation.

The application demonstrates:
- Use of abstract classes and interfaces
- Inheritance and method overriding
- Polymorphism via the `Reportable` interface
- Clean package structure (`models`, `interfaces`, main application)

The main application creates a list of issues, displays their details using the overridden `display()` method, and generates detailed reports for each issue using the
## Project's Structure :
```
day3project2/
├── day3project2 Notes.txt
└── src/
    └── main/
        └── java/
            ├── IssueTrackerApplication.java
            └── models/
                ├── Bug.java
                ├── Issue.java
                ├── Task.java
            └── interfaces/
                └── Reportable.java

```
## IssueTrackerApplication.java:
```
import java.util.ArrayList;
import java.util.List;
import models.Issue;
import models.Bug;
import models.Task;
import interfaces.Reportable;

public class IssueTrackerApplication {
    public static void main(String[] args){
        
        List<Issue> issues = new ArrayList<>();

        // Addition of Bug instances
        issues.add(new Bug(1, "NullPointer Exception", "Occurs on login", "High"));
        issues.add(new Bug(2, "UI Misalignment", "Button not centered", "Low"));

        // Addition of Task instances
        issues.add(new Task(3, "Implement Feature X", "Add new login method", "Alice", 5));
        issues.add(new Task(4, "Update Documentation", "Revise API docs", "Bob", 2));

        // Displaying issues 
        System.out.println("=== Issue List ===");
        for (Issue issue : issues) {
            issue.display();
        }

        // Generated reports using Reportable interface
        System.out.println("\n=== Reports ===");
        for (Issue issue : issues) {
            if (issue instanceof Reportable) {
                Reportable reportable = (Reportable) issue;
                reportable.generateReport();
            }
        }
    }
}
```
## models/Issue.java:
```
package models;

public abstract class Issue {
    private int id;
    private String title;
    private String description;
    private String status;

    //constructor
    public Issue(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = "Open"; //default status set as "Open";
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void closeStatus(){
        this.status = "Closed";
    }
    // abstract method to display issue details
    public abstract void display(); 
}
```
## models/Bug.java:
```
package models;
import interfaces.Reportable;
public class Bug extends Issue implements Reportable{
    private String severity;

    //constructor
    public Bug(int id, String title, String description, String severity) {
        super(id, title, description);
        this.severity = severity;
    }

    //getter
    public String getSeverity() {
        return severity;
    }

    @Override
    public void generateReport() {
        System.out.println("========== Bug Report ==========");
        System.out.println("Bug ID      : " + getId());
        System.out.println("Title       : " + getTitle());
        System.out.println("Description : " + getDescription());
        System.out.println("Status      : " + getStatus());
        System.out.println("Severity    : " + getSeverity());
        System.out.println("=========================================");
    }
    @Override
    public void display(){
        System.out.printf("ID: %d | %s - %s | Status: %s | Severity: %s%n", getId(), getTitle(), getDescription(), getStatus(), getSeverity());
    }

}
```
## models/Task.java:
```
package models;

import interfaces.Reportable;

public class Task extends Issue implements Reportable{
    private String assignedTo;
    private int due;

    //constructor
    public Task(int id, String title, String description, String assignedTo, int due) {
        super(id, title, description);
        this.assignedTo = assignedTo;
        this.due = due;
    }

    //getters
    public String getAssignedTo() {
        return assignedTo;
    }

    public int getDue() {
        return due;
    }

    @Override
    public void generateReport() {
        System.out.println("----- Task Report -----");
        System.out.println("Task ID      : " + getId());
        System.out.println("Title        : " + getTitle());
        System.out.println("Description  : " + getDescription());
        System.out.println("Status       : " + getStatus());
        System.out.println("Assigned To  : " + getAssignedTo());
        System.out.println("Due In       : " + getDue() + " days");
        System.out.println("--------------------------------");
    }
    @Override
    public void display(){
        System.out.printf("ID: %d | %s - %s | Status: %s | Assigned To: %s | Due in: %d days%n", getId(), getTitle(), getDescription(), getStatus(), getAssignedTo(), getDue());
    }
}
```
## interfaces/Reportable.java
```
package interfaces;

public interface Reportable {
    void generateReport();
} 