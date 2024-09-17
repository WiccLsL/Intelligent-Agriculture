package com.example.smartagriculture.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//实体类WeatherData 该类用于映射存储天气数据
public class WeatherData {

    private Integer id;
    private String city;
    private String description;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private LocalDateTime timestamp;

}
