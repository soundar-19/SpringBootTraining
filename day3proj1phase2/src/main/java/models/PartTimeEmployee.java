package models;

public class PartTimeEmployee extends Employee{
    private double hourlyRate;
    private int hoursWorked;
    private static final double TAX_RATE = 0.1; 
    public PartTimeEmployee(String name, String department, double hourlyRate, int hoursWorked) {
        super(name, department, "Part-Time");
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    public double getHourlyRate() {
        return hourlyRate;
    }
    public int getHoursWorked() {
        return hoursWorked;
    }
    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
    @Override
    public double calculateTax() {
        return calculateSalary() * TAX_RATE;
    }
}
