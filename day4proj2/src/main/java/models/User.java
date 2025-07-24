package models;

public class User {

    //properties
    private String userName;
    private int age;

    //constructor
    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    //getters
    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }
    
}
