package models;
import interfaces.Taxable;
public class FullTimeEmployee extends Employee implements Taxable {
    private double salary;

    public FullTimeEmployee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
    public double calculateTax(){
        return salary*0.2;
    }
    public double calculateSalary(){
        return salary;
    }
}
