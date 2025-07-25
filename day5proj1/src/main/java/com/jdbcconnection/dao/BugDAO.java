package com.jdbcconnection.dao;

import java.util.*;
import java.sql.*;
import com.jdbcconnection.models.Bug;
import com.jdbcconnection.util.DBUtil;

public class BugDAO {

    public void insertBug(Bug bug){
        Connection con = DBUtil.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO bugs(title,description,status) VALUES (?,?,?)");
            ps.setString(1,bug.getTitle());
            ps.setString(2,bug.getDescription());
            ps.setString(3,bug.getStatus());
            ps.executeUpdate();
            System.out.println("Bug inserted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Bug> getAllBugs(){

        List<Bug> bugList = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        try{
            PreparedStatement st = con.prepareStatement("SELECT * FROM bugs");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Bug b = new Bug();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setDescription(rs.getString("description"));
                b.setStatus(rs.getString("status"));
                bugList.add(b);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bugList;
    }
}
