package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.FertilizerWateringPlan;

import java.util.List;
import java.util.Optional;

public interface FertilizerWateringPlanService {

    List<FertilizerWateringPlan> getAllPlans();

    List<FertilizerWateringPlan> getPlanByCropId(Integer cropId);

    Optional<FertilizerWateringPlan> getPlanById(Integer id);

    FertilizerWateringPlan addPlan(FertilizerWateringPlan plan);

    FertilizerWateringPlan updatePlan(Integer id, FertilizerWateringPlan planDetails);

    void deletePlan(Integer id);
}
