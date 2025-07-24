package models;

import interfaces.Reportable;

public class Bug extends Issue implements Reportable{
    private String severity;

    // constructor
    public Bug(int id, String title, String description, String severity) {
        super(id, title, description);
        this.severity = severity;
    }
    // getter
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
