package com.example.smartagriculture.entity;

import jakarta.persistence.*;// 导入Jakarta Persistence API的包，用于支持Java对象与关系数据库之间的映射

import java.time.LocalDateTime;

@lombok.Data
@Entity

//实体类WeatherData 该类用于映射存储天气数据
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String description;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private LocalDateTime timestamp;

}
