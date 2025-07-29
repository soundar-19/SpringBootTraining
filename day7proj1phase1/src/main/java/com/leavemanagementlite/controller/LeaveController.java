package com.leavemanagementlite.controller;

import java.util.Scanner;
import com.leavemanagementlite.service.EmployeeService;
import com.leavemanagementlite.service.LeaveService;

public class LeaveController {
    private EmployeeService employeeService;
    private LeaveService leaveService;
    private Scanner sc;
    public LeaveController(){
        employeeService = new EmployeeService();
        leaveService = new LeaveService();
        sc = new Scanner(System.in);
    }
    public void start(){
        boolean canContinue = true;
        while(canContinue){
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    employeeService.createEmployee(sc);
                    break;
                case 2:
                    try{
                        employeeService.DeleteEmployee(sc);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    employeeService.displayAllEmployees();
                    break;
                case 4:
                    try{
                        leaveService.create(sc);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try{
                        leaveService.deleteById(sc);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    leaveService.updateStatusById(sc);
                    break;
                case 7:
                    leaveService.getAllByEmployeeId(sc);
                    break;
                case 8:
                    leaveService.getAllByStatus(sc);
                    break;
                case 9:
                    canContinue = false;
                    break;
                default:

            }
        }
    }
    public void showMenu(){
        System.out.println("\nLeave Management Lite");
        System.out.println("Select an option from below");
        System.out.println("1. Add Employee");
        System.out.println("2. Delete Employee");
        System.out.println("3. View All Employee");
        System.out.println("4. Create Leave Request");
        System.out.println("5. Delete Leave Request");
        System.out.println("6. Update Leave Request Status");
        System.out.println("7. View All Leave Request By an Employee");
        System.out.println("8. View All Leave Request By Status");
        System.out.println("9. Exit");
    }
}
