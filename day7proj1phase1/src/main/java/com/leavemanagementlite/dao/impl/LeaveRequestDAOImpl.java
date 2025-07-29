package com.leavemanagementlite.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.leavemanagementlite.dao.LeaveRequestDAO;
import com.leavemanagementlite.entity.LeaveRequest;
import com.leavemanagementlite.util.DBUtil;

public class LeaveRequestDAOImpl implements LeaveRequestDAO {
    public void create(LeaveRequest leaveRequest){
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO leave_request (employee_id,from_date,to_date,reason,status) VALUES (?,?,?,?,?)");
        ){
            ps.setInt(1, leaveRequest.getEmployeeId());
            ps.setDate(2, Date.valueOf(leaveRequest.getFromDate()));
            ps.setDate(3, Date.valueOf(leaveRequest.getToDate()));
            ps.setString(4, leaveRequest.getReason());
            ps.setString(5, leaveRequest.getStatus());
            ps.executeUpdate();
            System.out.println("Leave Request Created Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateStatusById(int requestId, String status){
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE leave_request SET status = ? WHERE id = ?");
        ){
            ps.setString(1, status);
            ps.setInt(2, requestId);
            ps.executeUpdate();
            System.out.println("Status updated sucessfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteById(int requestId){
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("Delete from leave_request where id = ?");
        ){
            ps.setInt(1, requestId);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<LeaveRequest> getAllByEmployeeId(int employeeId){
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM leave_request WHERE employee_id = ?");
        ){  
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setId(rs.getInt("id"));
                leaveRequest.setEmployeeId(rs.getInt("employee_id"));
                leaveRequest.setFromDate(rs.getDate("from_date").toLocalDate());
                leaveRequest.setToDate(rs.getDate("to_date").toLocalDate());
                leaveRequest.setReason(rs.getString("reason"));
                leaveRequest.setStatus(rs.getString("status"));
                leaveRequests.add(leaveRequest);
                }

        }catch(Exception e){
            e.printStackTrace();
        }
        return leaveRequests;
    }
    public List<LeaveRequest> getAllByStatus(String status){
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM leave_request WHERE status = ?");
        ){  
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setId(rs.getInt("id"));
                leaveRequest.setEmployeeId(rs.getInt("employee_id"));
                leaveRequest.setFromDate(rs.getDate("from_date").toLocalDate());
                leaveRequest.setToDate(rs.getDate("to_date").toLocalDate());
                leaveRequest.setReason(rs.getString("reason"));
                leaveRequest.setStatus(rs.getString("status"));
                leaveRequests.add(leaveRequest);
                }

        }catch(Exception e){
            e.printStackTrace();
        }
        return leaveRequests;
    }
    public LeaveRequest findById(int requestId){
        LeaveRequest leaveRequest = null;
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM leave_request WHERE id = ?");
        ){
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                leaveRequest = new LeaveRequest();
                leaveRequest.setId(rs.getInt("id"));
                leaveRequest.setEmployeeId(rs.getInt("employee_id"));
                leaveRequest.setFromDate(rs.getDate("from_date").toLocalDate());
                leaveRequest.setToDate(rs.getDate("to_date").toLocalDate());
                leaveRequest.setReason(rs.getString("reason"));
                leaveRequest.setStatus(rs.getString("status"));
                }
                
        }catch(Exception e){
            e.printStackTrace();
        }
        return leaveRequest;
    }
}
