package models;

import interfaces.Reportable;

public class Task extends Issue implements Reportable{
    private String assignedTo;
    private int due;

    // constructor
    public Task(int id, String title, String description, String assignedTo, int due) {
        super(id, title, description);
        this.assignedTo = assignedTo;
        this.due = due;
    }
    // getters
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
