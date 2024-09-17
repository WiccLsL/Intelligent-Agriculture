package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.WeatherData;
import com.example.smartagriculture.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{cityName}")
    public ResponseEntity<WeatherData> getWeatherByCity(@PathVariable String cityName) {
        WeatherData weatherData = weatherService.getWeatherData(cityName);
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

