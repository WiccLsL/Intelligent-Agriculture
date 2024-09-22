package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.FertilizerWateringPlan;
import com.example.smartagriculture.service.impl.FertilizerWateringPlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fertilizer-watering-plans")
public class FertilizerWateringPlanController {

    @Autowired
    private FertilizerWateringPlanServiceImpl planService;

    // 获取所有计划
    @GetMapping
    public ResponseEntity<List<FertilizerWateringPlan>> getAllPlans() {
        List<FertilizerWateringPlan> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    // 通过ID获取
    @GetMapping("/{cropId}")
    public ResponseEntity<List<FertilizerWateringPlan>> getPlanByCropId(@PathVariable Integer cropId) {
        List<FertilizerWateringPlan> plans = planService.getPlanByCropId(cropId);
        return ResponseEntity.ok(plans);
    }



    // 添加新计划
    @PostMapping
    public ResponseEntity<FertilizerWateringPlan> createPlan(@RequestBody FertilizerWateringPlan plan) {
        FertilizerWateringPlan createdPlan = planService.addPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    // 更新已有计划
    @PutMapping("/{id}")
    public ResponseEntity<FertilizerWateringPlan> updatePlan(@PathVariable Integer id, @RequestBody FertilizerWateringPlan planDetails) {
        try {
            FertilizerWateringPlan updatedPlan = planService.updatePlan(id, planDetails);
            return ResponseEntity.ok(updatedPlan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 删除计划
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
