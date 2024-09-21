package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.entity.UserCrop;
import com.example.smartagriculture.service.CropService;
import com.example.smartagriculture.service.impl.UserCropServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private UserCropServiceImpl userCropService;

    @GetMapping
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/{id}")
    public Crop getCropById(@PathVariable Integer id) {
        return cropService.getCropById(id);
    }

    @PostMapping
    public ResponseEntity<Crop> createCrop(@RequestBody Crop crop) {
        Crop createdCrop = cropService.createCrop(crop);
        if (crop != null) {
            return ResponseEntity.ok().body(crop);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @PutMapping("/{id}")
    public void updateCrop(@PathVariable Integer id, @RequestBody Crop newCropData) {
        cropService.updateCrop(id, newCropData);
    }

    @DeleteMapping("/{id}")
    public void deleteCrop(@PathVariable Integer id) {
        cropService.deleteCrop(id);
    }

    // 通过用户 ID 获取该用户的所有作物
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Crop>> getCropsByUserId(@PathVariable Integer userId) {
        List<Crop> userCrops = userCropService.getCropsByUserId(userId);
        return ResponseEntity.ok(userCrops);
    }
}
