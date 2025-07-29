package com.leavemanagementlite.dao;

import java.util.List;
import com.leavemanagementlite.entity.Employee;

public interface EmployeeDAO {
    public void insert(Employee employee);
    public List<Employee> fetchAll();
    public Employee findById(int id);
    public void deleteById(int id);
}
