package services;
import exceptions.InvalidAgeException;
import models.User;

public class UserService {

    public void registerUser(User user) throws InvalidAgeException{
        //throw exception if age is less than 18
        if(user.getAge()<18) throw new InvalidAgeException("Registration failed. User must be atleast 18 years old");

        //register user
        System.out.println("User "+user.getUserName()+" has been registered successfully");
    }
}
