package com.leavemanagementlite;

import com.leavemanagementlite.controller.LeaveController;

public class LeaveManagementLite 
{
    public static void main( String[] args )
    {
        LeaveController leaveController = new LeaveController();
        leaveController.start();
    }
}
