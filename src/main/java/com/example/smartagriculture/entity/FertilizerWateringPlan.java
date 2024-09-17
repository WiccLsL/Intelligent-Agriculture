package com.example.smartagriculture.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@lombok.Data
@Entity
@Table(name = "fertilizer_watering_plans")  // 推荐使用小写表名
public class FertilizerWateringPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(nullable = false)
    private String frequency;  // 频率（比如：每日、每周）

    @Column(nullable = false)
    private LocalTime time;  // 浇水或施肥的时间

    private String conditions;  // 可选的条件字段（比如：下雨时不浇水）
}
