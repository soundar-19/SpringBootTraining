package com.jdbcconnection.main;

import java.util.List;
import com.jdbcconnection.dao.BugDAO;
import com.jdbcconnection.models.Bug;

public class Main {
    public static void main(String[] args){
        BugDAO dao = new BugDAO();
        dao.insertBug(new Bug("Login issue","Cannot login with the credintials","Open"));
        dao.insertBug(new Bug("Page not loading","Infinite loader","Open"));
        dao.insertBug(new Bug("404 Error", "Requested page not found", "Open"));

        List<Bug> list = dao.getAllBugs();
        for(Bug bug : list){
            System.out.println("ID: "+bug.getId());
            System.out.println("Title: "+bug.getTitle());
            System.out.println("Description: "+bug.getDescription());
            System.out.println("Status: "+bug.getStatus());
        }
    }
}
