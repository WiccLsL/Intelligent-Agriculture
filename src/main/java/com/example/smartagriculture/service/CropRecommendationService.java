package com.example.smartagriculture.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CropRecommendationService {

    @Value("${flask.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CropRecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecommendedCrop(Map<String, Object> inputData) {
        Map<String, Object> response = restTemplate.postForObject(apiUrl, inputData, Map.class);
        if (response != null && response.get("prediction") != null) {
            // 获取预测结果，假设是作物名称
            return (String) response.get("prediction");
        }
        return null;
    }
}
