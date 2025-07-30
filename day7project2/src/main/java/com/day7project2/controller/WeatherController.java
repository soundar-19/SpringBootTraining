package com.day7project2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @GetMapping("/weather/today")
    public String getWeather() {
        return "It's a sunny day!";
    }
    @GetMapping("/weather/tomorrow")
    public String getTomorrowWeather() {    
        return "Tomorrow will be rainy!";
    }
    @GetMapping("/city/{cityName}")
    public String getCityWeather(@PathVariable String cityName) {
        return "The weather in " + cityName + " is nice!";
    }
}
