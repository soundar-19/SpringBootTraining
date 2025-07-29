package com.leavemanagementlite.service;

import java.util.List;
import java.util.Scanner;

import com.leavemanagementlite.dao.impl.EmployeeDAOImpl;
import com.leavemanagementlite.entity.Employee;
import com.leavemanagementlite.exceptions.EmployeeNotFoundException;

public class EmployeeService {
    private EmployeeDAOImpl employeeDAOImpl;
    public EmployeeService(){
        employeeDAOImpl = new EmployeeDAOImpl();
    }
    public void createEmployee(Scanner sc){
        System.out.println("Enter the Employee Name: ");
        String name = sc.nextLine();
        System.out.println("Enter the Employee Department: ");
        String department = sc.nextLine();
        System.out.println("Enter the Employee's Email Address: ");
        String email = sc.nextLine();
        Employee employee = new Employee(name, department, email);
        employeeDAOImpl.insert(employee);
    }
    public void DeleteEmployee(Scanner sc) throws EmployeeNotFoundException{
        System.out.println("Enter Employee Id: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        Employee employee = employeeDAOImpl.findById(employeeId);
        if(employee == null) throw new EmployeeNotFoundException("Employee Not Found");
        employeeDAOImpl.deleteById(employeeId);
    }
    public void displayAllEmployees(){
        List<Employee> employees = employeeDAOImpl.fetchAll();
        if(employees.isEmpty()) System.out.println("No Employee is registered");
        else{
            for(Employee employee : employees){
                System.out.println("\nEmployee Details");
                System.out.println("ID: "+employee.getEmployeeId());
                System.out.println("Name: "+employee.getEmployeeName());
                System.out.println("Department: "+employee.getDepartment());
                System.out.println("Email: "+employee.getEmployeeEmail());
            }
        }
    }
}
