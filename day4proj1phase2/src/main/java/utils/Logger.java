package utils;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class Logger {
    FileWriter fileWriter;
    public Logger(String fileName) {
        try{
            fileWriter = new FileWriter(fileName, true);
        }
        catch(Exception e){
            System.out.println("Error while initializing logger: " + e.getMessage());
        }
    }
    public void log(String userName, int age, String message) {
        try {
            fileWriter.write(LocalDateTime.now()+" User: " + userName + ", Age: " + age + ", Error: " + message + "\n");
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println("Error while logging: " + e.getMessage());
        }
    }
}
