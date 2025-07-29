package com.leavemanagementlite.entity;

public class Employee {
    //properties
    private int employeeId;
    private String employeeName;
    private String department;
    private String employeeEmail;

    //constructors
    public Employee(){}

    public Employee(String employeeName, String department, String employeeEmail) {
        this.employeeName = employeeName;
        this.department = department;
        this.employeeEmail = employeeEmail;
    }
    //getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    
}
