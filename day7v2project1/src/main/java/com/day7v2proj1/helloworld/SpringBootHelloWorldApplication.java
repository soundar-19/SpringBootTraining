package com.day7v2proj1.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringBootHelloWorldApplication {
    public static void main(String[] args) {
       // System.out.println("Hello World from Spring Boot!");
       SpringApplication.run(SpringBootHelloWorldApplication.class, args);
    }
}
