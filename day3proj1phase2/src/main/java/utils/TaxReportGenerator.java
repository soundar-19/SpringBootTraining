package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Employee;

public class TaxReportGenerator {
    public List<Employee> employees;
    public TaxReportGenerator(List<Employee> employees){
        this.employees = employees;
    }
    public void displayAllEmployeeDetails(){
        for(Employee employee : employees){
            employee.displayDetails();
        }
    }
    public void displayEmployeesSortedByTax(){
        System.out.println("Employees Sorted By Tax");
        List<Employee> tempList = new ArrayList<>(employees);
        tempList.sort((e1, e2) -> Double.compare(e1.calculateTax(), e2.calculateTax()));
        for(Employee employee : tempList){
            employee.displayDetails();
        }
    }
    public void calculateTotalTax(){
        double totalTax = 0;
        for(Employee employee : employees){
            totalTax += employee.calculateTax();
        }
        System.out.printf("\nTotal Tax Collected: %.1f%n\n", totalTax);
    }
    public void displayDepartmentWiseEmployees(){
        Map<String, List<Employee>> departmentWiseEmployees = new HashMap<>();
        for(Employee employee : employees){
            departmentWiseEmployees.putIfAbsent(employee.getDepartment(), new ArrayList<>());
            departmentWiseEmployees.get(employee.getDepartment()).add(employee);
        }
        for(String department : departmentWiseEmployees.keySet()){
            double totalTax = 0;
            System.out.println("\n"+department+" Department Employee List\n");
            List<Employee> employeeList = departmentWiseEmployees.get(department);
            for(Employee employee : employeeList){
                employee.displayDetails();
                totalTax += employee.calculateTax();
            }
            System.out.println("\nTotal Tax : "+totalTax);
        }
    }
}
