package main;

import utils.Logger;
import models.Ticket;
import services.TicketService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Initializing Ticket Service
        TicketService ticketService = new TicketService();
        
        //Initializing Scanner class
        Scanner sc = new Scanner(System.in);

        //Logger initialization
        Logger logger = new Logger("error.log");

        do{
            System.out.println("Choose an option:");
            System.out.println("1. Add Ticket");
            System.out.println("2. List Tickets");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); //consuming newLine

            switch (choice) {
                case 1:
                    //Input for Ticket
                    System.out.print("Enter username: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter age: ");
                    if(!sc.hasNextInt()) {
                        System.out.println("Invalid input. Age must be a number.");
                        sc.nextLine(); // consume invalid input
                        continue; // skip to next iteration
                    }
                    int age = sc.nextInt();
                    sc.nextLine(); //consuming newLine
                    System.out.print("Enter source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine();

                    //Creating Ticket object
                    Ticket ticket = new Ticket(userName, age, source, destination);
                    try{
                        ticketService.bookTicket(ticket);
                    }
                    catch(Exception e){

                        //Logging the error
                        logger.log(ticket,e.getMessage());
                        System.out.println(e.getMessage());

                    }
                    break;
                case 2:
                    ticketService.displayAllTickets();
                    break;
                case 3:
                    System.out.println("Exitting....");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }

        }while(true);
        
    }
}
