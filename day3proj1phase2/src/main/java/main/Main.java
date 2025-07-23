package main;
import models.*;
import java.util.ArrayList;
import java.util.List;

import utils.TaxReportGenerator;
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Full Time Employee Creation
        employees.add(new FullTimeEmployee("SR", "Engineering", 25000D));
        employees.add(new FullTimeEmployee("S", "Engineering", 134000D));
        employees.add(new FullTimeEmployee("R", "Engineering", 309000D));
        employees.add(new FullTimeEmployee("Sid", "Engineering", 120090D));
        employees.add(new FullTimeEmployee("Senthil", "Engineering", 9990D));
        // Part Time Employee Creation
        employees.add(new PartTimeEmployee("Jolly", "Sales", 143, 9));
        employees.add(new PartTimeEmployee("John", "Sales", 142, 3));
        employees.add(new PartTimeEmployee("Doe", "Sales", 130, 31));
        employees.add(new PartTimeEmployee("Kumar", "Sales", 103, 69));
        employees.add(new PartTimeEmployee("Jai", "Sales", 993, 30));

        // Add employees to the list
        TaxReportGenerator reportGenerator = new TaxReportGenerator(employees);
        reportGenerator.displayAllEmployeeDetails();
        reportGenerator.calculateTotalTax();
        reportGenerator.displayEmployeesSortedByTax();
        reportGenerator.displayDepartmentWiseEmployees();
    }
}
