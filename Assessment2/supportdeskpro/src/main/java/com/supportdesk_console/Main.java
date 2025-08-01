package com.supportdesk_console;

import java.util.List;
import java.util.Scanner;

import com.supportdeskpro.supportdeskpro.domain.Ticket;
import com.supportdeskpro.supportdeskpro.domain.User;
import com.supportdesk_console.exception.UserNotFoundException;
import com.supportdesk_console.exception.TicketNotFoundException;
import com.supportdesk_console.service.TicketService;
import com.supportdesk_console.service.UserService;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        listUsers();
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        listTickets();
                        break;
                    case 4:
                        createTicket();
                        break;
                    case 5:
                        assignTicket();
                        break;
                    case 6:
                        closeTicket();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (UserNotFoundException | TicketNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nSupport Desk Console Application");
        System.out.println("1. List Users");
        System.out.println("2. Create User");
        System.out.println("3. List Tickets");
        System.out.println("4. Create Ticket");
        System.out.println("5. Assign Ticket");
        System.out.println("6. Close Ticket");
        System.out.println("7. Exit");
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static void listUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getUserName() + ", Email: " + user.getEmail());
            }
        }
    }

    private static void createUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        userService.createUser(user);
        System.out.println("User created with ID: " + user.getId());
    }

    private static void listTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            for (Ticket ticket : tickets) {
                System.out.println("ID: " + ticket.getId() + ", Title: " + ticket.getTitle() + ", Status: " + ticket.getStatus() + ", Assigned To: " + ticket.getAssignedTo());
            }
        }
    }

    private static void createTicket() {
        System.out.print("Enter ticket title: ");
        String title = scanner.nextLine();
        System.out.print("Enter ticket description: ");
        String description = scanner.nextLine();
        System.out.print("Enter user ID for ticket: ");
        Long userId = Long.parseLong(scanner.nextLine());
        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("User not found. Ticket creation failed.");
            return;
        }
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setStatus("open");
        ticket.setUser(user);
        ticketService.createTicket(ticket);
        System.out.println("Ticket created with ID: " + ticket.getId());
    }

    private static void assignTicket() {
        Long ticketId = (long) readInt("Enter ticket ID to assign: ");
        System.out.print("Enter name to assign ticket to: ");
        String assignedTo = scanner.nextLine();
        ticketService.assignTicketById(ticketId, assignedTo);
    }

    private static void closeTicket() {
        Long ticketId = (long) readInt("Enter ticket ID to close: ");
        ticketService.closeTicket(ticketId);
        System.out.println("Ticket closed.");
    }
}
