package models;

import interfaces.Taxable;

public class PartTimeEmployee extends Employee implements Taxable{

    private double hourlyRate;
    private double duration;
    public PartTimeEmployee(String name, double hourlyRate, double duration) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.duration = duration;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
    public double getDuration() {
        return duration;
    }
    public double calculateSalary(){
        return hourlyRate*duration;
    }
    public double calculateTax(){
        return calculateSalary()*0.1;
    }
}
