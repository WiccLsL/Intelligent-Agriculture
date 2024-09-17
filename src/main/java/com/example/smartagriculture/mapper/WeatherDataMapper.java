package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.WeatherData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeatherDataMapper {
    int insertWeatherData(WeatherData weatherData);
}
