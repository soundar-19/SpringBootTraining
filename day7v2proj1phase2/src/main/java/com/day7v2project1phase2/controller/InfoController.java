package com.day7v2project1phase2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/info")
    public String getInfo() {
        return "This is a Simple SpringBoot Application.";
    }
}
