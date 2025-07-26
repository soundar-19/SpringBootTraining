package com.day5proj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.day5proj2.models.Bug;
import com.day5proj2.util.DBUtil;

public class BugDAO {
    public void insertBug(Bug bug){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO bugs(title,description,status) VALUES(?,?,?)");
            ps.setString(1, bug.getTitle());
            ps.setString(2,bug.getDescription());
            ps.setString(3, bug.getStatus());
            ps.executeUpdate();
            System.out.println("Bug is inserted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteBugById(String id){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM bugs where id = ?");
            ps.setInt(1,Integer.parseInt(id));
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0) System.out.println("Bug with id "+id+" is deleted");
            else System.out.println("No Such Bug Exist");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateBugStatusById(String id,String status){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE bugs SET status = ? where id = ?");
            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(id));
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0) System.out.println("Bug Status Updated Successfully");
            else System.out.println("No Such Bug Exist");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Bug findBugById(String id){
        Bug bug = new Bug();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM bugs WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bug = new Bug(rs.getString("title"),rs.getString("description"),rs.getString("status"));
                bug.setId(rs.getInt("id"));
            }else{
                System.out.println("Bug with ID "+id+" not found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return bug;
    }
    public List<Bug> getAllBugs(){
        List<Bug> bugList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM bugs");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bug bug = new Bug();
                bug.setId(rs.getInt("id"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setStatus(rs.getString("status"));
                bugList.add(bug);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return bugList;
    }
}
