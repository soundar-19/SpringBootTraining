package com.leavemanagementlite.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leavemanagementlite.dao.EmployeeDAO;
import com.leavemanagementlite.entity.Employee;
import com.leavemanagementlite.util.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

    public void insert(Employee employee){
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO employee(name,department,email) VALUES(?,?,?)");
        ){
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getDepartment());
            ps.setString(3, employee.getEmployeeEmail());
            ps.executeUpdate();
            System.out.println("Employee created");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Employee> fetchAll(){
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee");
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setEmployeeName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setEmployeeEmail(rs.getString("email"));
                employees.add(employee);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return employees;
    }
    public Employee findById(int id){
        Employee employee = null;
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE id=?");
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setEmployeeName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setEmployeeEmail(rs.getString("email"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return employee;
    }
    public void deleteById(int id){
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id=?");
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Employee deleted");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
