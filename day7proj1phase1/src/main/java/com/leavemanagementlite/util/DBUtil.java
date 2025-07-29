package com.leavemanagementlite.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/leavemanagement","postgres","jdbc");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
