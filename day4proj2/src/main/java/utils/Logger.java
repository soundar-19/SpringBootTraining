package utils;

import java.io.FileWriter;
import java.time.LocalDate;

import models.Ticket;

public class Logger {
    private FileWriter fileWriter;
    public Logger(String fileName){
        try{
            fileWriter = new FileWriter(fileName,true);
        }catch(Exception e){
            System.out.println("Unable to Create the "+fileName+" file");
        }
    }
    public void log(Ticket ticket,String error) {
        try{
            fileWriter.write(LocalDate.now() + " User: " + ticket.getUserName() + ", Age: " + ticket.getAge() + ", Source: " + ticket.getSource() + ", Destination: " + ticket.getDestination() + ", Error: " + error + "\n");
            fileWriter.flush(); // Ensure the data is written to the file
        }
        catch(Exception e){
            System.out.println("Unable to log. Try again Later");
        }
    }
}
