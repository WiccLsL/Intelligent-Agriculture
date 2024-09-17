package com.example.smartagriculture.entity;

import jakarta.persistence.*;

@lombok.Data
@Entity
@Table(name = "UserCrops")
public class UserCrop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    // Getters and Setters
}
