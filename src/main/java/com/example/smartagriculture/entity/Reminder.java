package com.example.smartagriculture.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reminder {

    private Integer id;

    private Crop cropId;

    private String reminderMessage;

    private LocalDateTime reminderDate;

    private String status;

    // Getters and Setters
}
