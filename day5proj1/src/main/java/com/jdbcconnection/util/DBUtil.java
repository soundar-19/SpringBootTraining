package com.jdbcconnection.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","jdbc");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
