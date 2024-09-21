package com.example.smartagriculture.service.impl;

import com.example.smartagriculture.entity.FertilizerWateringPlan;
import com.example.smartagriculture.mapper.FertilizerWateringPlanMapper;
import com.example.smartagriculture.service.FertilizerWateringPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FertilizerWateringPlanServiceImpl implements FertilizerWateringPlanService {

    @Autowired
    private FertilizerWateringPlanMapper planMapper;

    @Override
    public List<FertilizerWateringPlan> getAllPlans() {
        return planMapper.getAllPlans();
    }

    @Override
    public Optional<FertilizerWateringPlan> getPlanById(Integer cropId) {
        FertilizerWateringPlan plan = planMapper.getPlanById(cropId);
        return Optional.ofNullable(plan);
    }

    @Override
    public FertilizerWateringPlan addPlan(FertilizerWateringPlan plan) {
        planMapper.addPlan(plan);
        return plan;
    }

    @Override
    public FertilizerWateringPlan updatePlan(Integer id, FertilizerWateringPlan planDetails) {
        FertilizerWateringPlan existingPlan = planMapper.getPlanById(id);
        if (existingPlan != null) {
            existingPlan.setCropId(planDetails.getCropId());
            existingPlan.setFrequency(planDetails.getFrequency());
            existingPlan.setTime(planDetails.getTime());
            existingPlan.setConditions(planDetails.getConditions());
            planMapper.updatePlan(existingPlan);
            return existingPlan;
        } else {
            throw new RuntimeException("Plan with id " + id + " not found");
        }
    }

    @Override
    public void deletePlan(Integer id) {
        planMapper.deletePlan(id);
    }
}
