package com.example.smartagriculture.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@lombok.Data
@Entity
@Table(name = "Crops")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;

    @Column(nullable = false)
    private String cropName;

    @Column(nullable = false)
    private LocalDate plantingDate;

    @Column(nullable = false)
    private LocalDate expectedHarvestDate;
}
