package com.leavemanagementlite.dao;

import java.util.List;
import com.leavemanagementlite.entity.LeaveRequest;

public interface LeaveRequestDAO {

    public void create(LeaveRequest leaveRequest);
    public void updateStatusById(int requestId, String status);
    public void deleteById(int requestId);
    public List<LeaveRequest> getAllByEmployeeId(int employeeId);
    public List<LeaveRequest> getAllByStatus(String status);
    public LeaveRequest findById(int requestId);
} 
