package com.example.smartagriculture.repository;

import com.example.smartagriculture.entity.FertilizerWateringPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FertilizerWateringPlanRepository extends JpaRepository<FertilizerWateringPlan, Long> {
    // 默认的 CRUD 操作不需要额外定义
}
