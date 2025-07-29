package com.leavemanagementlite.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.leavemanagementlite.dao.impl.EmployeeDAOImpl;
import com.leavemanagementlite.dao.impl.LeaveRequestDAOImpl;
import com.leavemanagementlite.entity.LeaveRequest;
import com.leavemanagementlite.exceptions.EmployeeNotFoundException;
import com.leavemanagementlite.exceptions.InvalidLeaveDateException;
import com.leavemanagementlite.exceptions.LeaveRequestNotFoundException;

public class LeaveService {
    private EmployeeDAOImpl employeeDAOImpl;
    private LeaveRequestDAOImpl leaveRequestDAOImpl;
    private DateTimeFormatter formatter;
    public LeaveService(){
        this.employeeDAOImpl = new EmployeeDAOImpl();
        this.leaveRequestDAOImpl = new LeaveRequestDAOImpl();
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    public void create(Scanner sc)throws InvalidLeaveDateException{
        System.out.println("Enter Employee ID");
        int employeeId = sc.nextInt();
        sc.nextLine();
        try{
            if(employeeDAOImpl.findById(employeeId) == null) throw new EmployeeNotFoundException("Employee Not Found");
            System.out.println("Enter the starting date of the leave");
            String fDate = sc.nextLine();
            LocalDate fromDate = LocalDate.parse(fDate, formatter);
            System.out.println("Enter the ending date of the leave");
            String tDate = sc.nextLine();
            LocalDate toDate = LocalDate.parse(tDate, formatter);
            
            if(fromDate.isAfter(toDate)) throw new InvalidLeaveDateException("From Date Should come before To Date");
            
            System.out.println("Enter the reason for the leave");
            String reason = sc.nextLine();
            
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setEmployeeId(employeeId);
            leaveRequest.setFromDate(fromDate);
            leaveRequest.setToDate(toDate);
            leaveRequest.setReason(reason);
            leaveRequest.setStatus("Pending");
            leaveRequestDAOImpl.create(leaveRequest);
            
        }catch(DateTimeParseException e){
            throw new InvalidLeaveDateException("The Date must be in the \"dd-mm-yyyy\"format");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    public void updateStatusById(Scanner sc){
        System.out.println("Enter the Request Id: ");
        int requestId = sc.nextInt();
        sc.nextLine();
        try{
            LeaveRequest leaveRequest = leaveRequestDAOImpl.findById(requestId);
            if(leaveRequest == null) throw new LeaveRequestNotFoundException("No Such Leave Request");
            System.out.println("Enter the status");
            String status = sc.nextLine();
            leaveRequestDAOImpl.updateStatusById(requestId, status);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteById(Scanner sc){
        System.out.println("Enter the Request Id: ");
        int requestId = sc.nextInt();
        sc.nextLine();
        try{
            LeaveRequest leaveRequest = leaveRequestDAOImpl.findById(requestId);
            if(leaveRequest == null) throw new LeaveRequestNotFoundException("No Such Leave Request");
            leaveRequestDAOImpl.deleteById(requestId);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void getAllByEmployeeId(Scanner sc){
        System.out.println("Enter the Employee Id: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        List<LeaveRequest> leaveRequests = leaveRequestDAOImpl.getAllByEmployeeId(employeeId);
        if(leaveRequests.isEmpty()) System.out.println("No Leave Requests Found");
        else{
            for(LeaveRequest leaveRequest:leaveRequests){
                System.out.println("------------------------------------------------");
                System.out.println("Leave Request Details: ");
                System.out.println("Leave Request Id: "+leaveRequest.getId());
                System.out.println("Employee Id: "+leaveRequest.getEmployeeId());
                System.out.println("From Date: "+leaveRequest.getFromDate());
                System.out.println("To Date: "+leaveRequest.getToDate());
                System.out.println("Reason: "+leaveRequest.getReason());
                System.out.println("Status: "+leaveRequest.getStatus());
                System.out.println("------------------------------------------------");
            }
        }
    }
    public void getAllByStatus(Scanner sc){
        System.out.println("Enter the Status: ");
        String status = sc.nextLine();
        List<LeaveRequest> leaveRequests = leaveRequestDAOImpl.getAllByStatus(status);
        if(leaveRequests.isEmpty()) System.out.println("No Leave Requests Found");
        else{
            for(LeaveRequest leaveRequest:leaveRequests){
                System.out.println("------------------------------------------------");
                System.out.println("Leave Request Details: ");
                System.out.println("Leave Request Id: "+leaveRequest.getId());
                System.out.println("Employee Id: "+leaveRequest.getEmployeeId());
                System.out.println("From Date: "+leaveRequest.getFromDate());
                System.out.println("To Date: "+leaveRequest.getToDate());
                System.out.println("Reason: "+leaveRequest.getReason());
                System.out.println("Status: "+leaveRequest.getStatus());
                System.out.println("------------------------------------------------");
            }
        }
    }
    
}
