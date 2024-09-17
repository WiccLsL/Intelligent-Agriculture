package com.example.smartagriculture.controller;

import com.example.smartagriculture.service.CropRecommendationService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/crop")
public class CropRecommendationController {

    private final CropRecommendationService recommendationService;

    public CropRecommendationController(CropRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/recommend")
    public String recommendCrop(@RequestBody Map<String, Object> inputData) {
        return recommendationService.getRecommendedCrop(inputData);
    }
}
