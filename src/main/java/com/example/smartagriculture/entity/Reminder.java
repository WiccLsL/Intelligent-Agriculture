package com.example.smartagriculture.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@lombok.Data
@Table(name = "Reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(nullable = false)
    private String reminderMessage;

    @Column(nullable = false)
    private LocalDateTime reminderDate;

    @Column(nullable = false)
    private String status;

    // Getters and Setters
}
