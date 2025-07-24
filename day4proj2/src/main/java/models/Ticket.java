package models;

public class Ticket extends User {
    //properties
    private String source;
    private String destination;

    //constructor
    public Ticket(String userName, int age, String source, String destination) {
        super(userName, age);
        this.source = source;
        this.destination = destination;
    }

    //getters
    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void displayTicket() {
        System.out.println("Ticket Information");
        System.out.println("UserName: " + getUserName());
        System.out.println("Age: " + getAge());
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println();
    }

}
