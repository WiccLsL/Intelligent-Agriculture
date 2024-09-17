package com.example.smartagriculture.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Crop {

    private Integer id;

    private String cropName;

    private LocalDate plantingDate;

    private LocalDate expectedHarvestDate;
}
