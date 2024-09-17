package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.WeatherData;

public interface WeatherService {
    WeatherData getWeatherData(String city) throws Exception;
}
