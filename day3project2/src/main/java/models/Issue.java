package models;

public abstract class Issue {
    private int id;
    private String title;
    private String description;
    private String status;

    // constructor
    public Issue(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = "Open"; //default status set as "Open";
    }

    // getters
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
