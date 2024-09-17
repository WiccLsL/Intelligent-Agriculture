package com.example.smartagriculture.entity;

// CropRecommendation 实体类，用于存储推荐的农作物种植方案


import jakarta.persistence.*;

@Entity
@lombok.Data
public class CropRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String cropType;
    private String recommendation;
}

