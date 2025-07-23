package models;

import interfaces.Taxable;
public abstract class Employee implements Taxable {
    static int totalEmployees = 0;
    private int employeeId;
    private String name;
    private String department;
    private String type;

    public Employee(String name, String department, String type) {
        this.name = name;
        this.department = department;
        this.type = type;
        this.employeeId = totalEmployees+100;
        totalEmployees++;
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getType() {
        return type;
    }
    public abstract double calculateSalary();
    public void displayDetails() {
        System.out.printf("[ID: %d] %s - %s | Department : %s | Salary: %.0f | Tax: %.1f%n",
            getEmployeeId(), getType(), getName(), getDepartment(), calculateSalary(), calculateTax());
    }
}
