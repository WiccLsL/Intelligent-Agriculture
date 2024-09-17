package com.example.smartagriculture.entity;

// CropRecommendation 实体类，用于存储推荐的农作物种植方案

import lombok.Data;

@Data
public class CropRecommendation {

    private Integer id;
    private String city;
    private String cropType;
    private String recommendation;
}

