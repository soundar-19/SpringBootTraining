package models;

public class FullTimeEmployee extends Employee {
    private double salary;
    private static final double TAX_RATE = 0.2;
    public FullTimeEmployee(String name, String department, double salary) {
        super(name, department, "Full-Time");
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
    @Override
    public double calculateSalary(){
        return salary;
    }
    @Override
    public double calculateTax() {
        return calculateSalary() * TAX_RATE;
    }
}
