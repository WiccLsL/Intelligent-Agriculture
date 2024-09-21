package com.example.smartagriculture.entity;

import lombok.Data;

import java.time.LocalTime;

@Data
public class FertilizerWateringPlan {

    private Integer id;

    private Integer cropId;

    private String frequency;  // 频率（比如：每日、每周）

    private LocalTime time;  // 浇水或施肥的时间

    private String conditions;  // 可选的条件字段（比如：下雨时不浇水）
}
