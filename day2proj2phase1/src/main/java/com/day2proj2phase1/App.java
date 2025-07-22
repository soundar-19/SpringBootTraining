package com.day2proj2phase1;

public class App 
{
    public static void main( String[] args )
    {
        //creating users
        User user1 = new User(1, "Soundar", "Soundar@gmail.com");
        User user2 = new User(2,"Raja","raja@gmail.com");

        //creating tickets
        Ticket ticket1 = new Ticket(1,"Network Issue","Experiencing Slow Network",user1);
        Ticket ticket2 = new Ticket(2,"Software Issue","Too Many Bugs in the App",user2);
        Ticket ticket3 = new Ticket(3,"Hardware Issue","Device Malfunction",user1);
        
        //Displaying Ticket Details
        ticket1.displayDetails();
        ticket2.displayDetails();
        ticket3.displayDetails();
    }
}
