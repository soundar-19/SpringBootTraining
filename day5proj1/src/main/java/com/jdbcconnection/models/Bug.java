package com.jdbcconnection.models;

public class Bug {
    
    //properties
    private int id;
    private String title;
    private String description;
    private String status;

    //constructor
    //public Bug(){} use dummy constructor to handle errors if needed

    public Bug(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Bug() {}

    //getters
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

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
