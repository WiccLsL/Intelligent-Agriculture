package com.example.smartagriculture.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reminder {

    private Integer id;

    private Integer cropId;  // 修改为 Integer 类型

    private String reminderMessage;

    private LocalDateTime reminderDate;

    private String status;

    // Getters and Setters
}
