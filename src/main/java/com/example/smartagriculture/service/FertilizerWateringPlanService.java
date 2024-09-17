package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.FertilizerWateringPlan;
import com.example.smartagriculture.repository.FertilizerWateringPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FertilizerWateringPlanService {

    @Autowired
    private FertilizerWateringPlanRepository planRepository;

    // 获取所有计划
    public List<FertilizerWateringPlan> getAllPlans() {
        return planRepository.findAll();
    }

    // 通过ID获取单个计划
    public Optional<FertilizerWateringPlan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    // 添加新计划
    public FertilizerWateringPlan addPlan(FertilizerWateringPlan plan) {
        return planRepository.save(plan);
    }

    // 更新已有计划
    public FertilizerWateringPlan updatePlan(Long id, FertilizerWateringPlan planDetails) {
        Optional<FertilizerWateringPlan> existingPlan = planRepository.findById(id);
        if (existingPlan.isPresent()) {
            FertilizerWateringPlan plan = existingPlan.get();
            plan.setCrop(planDetails.getCrop());
            plan.setFrequency(planDetails.getFrequency());
            plan.setTime(planDetails.getTime());
            plan.setConditions(planDetails.getConditions());
            return planRepository.save(plan);
        } else {
            throw new RuntimeException("Plan with id " + id + " not found");
        }
    }

    // 删除计划
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
