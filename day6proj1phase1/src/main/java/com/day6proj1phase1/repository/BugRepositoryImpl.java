package com.day6proj1phase1.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.day6proj1phase1.entity.Bug;
import com.day6proj1phase1.util.DBUtil;

public class BugRepositoryImpl implements BugRepository{
    public void insertBug(Bug bug){
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Bugs(title,description,status) VALUES(?,?,?)");
        ){
            ps.setString(1, bug.getTitle());
            ps.setString(2, bug.getDescription());
            ps.setString(3, bug.getStatus());
            ps.executeUpdate();
            System.out.println("Bug inserted Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Bug> getAllBugs(){
        List<Bug> bugList = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Bugs");
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bug bug = new Bug();
                bug.setId(rs.getInt("id"));
                bug.setDescription(rs.getString("description"));
                bug.setTitle(rs.getString("title"));
                bug.setStatus(rs.getString("status"));
                bugList.add(bug);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bugList;
    }
}
