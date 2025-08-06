package com.student_course_management_system.dto;

public class StudentResponseDTO {
    private Long id;
    private String name;
    private Long rollNumber;
    private String email;

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
