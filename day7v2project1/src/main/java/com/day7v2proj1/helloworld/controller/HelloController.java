package com.day7v2proj1.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World from Spring Boot!";
    }
    @GetMapping("/greet")
    public String greet() {
        return "Greetings from Spring Boot!";
    }
    
}
