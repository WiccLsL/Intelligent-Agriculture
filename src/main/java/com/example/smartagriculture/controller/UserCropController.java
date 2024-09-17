package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.UserCrop;
import com.example.smartagriculture.service.impl.UserCropServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userCrops")
public class    UserCropController {

    @Autowired
    private UserCropServiceImpl userCropService;

    // 添加用户作物关系
    @PostMapping
    public ResponseEntity<String> createUserCrop(@RequestBody UserCrop userCrop) {
        try {
            if (userCrop.getCropId() == null || userCrop.getUserId() == null) {
                return ResponseEntity.badRequest().body("Crop or User cannot be null");
            }
            userCropService.addUserCrop(userCrop);
            return ResponseEntity.status(HttpStatus.CREATED).body("作物添加成功！");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 删除用户作物关系
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCrop(@PathVariable Integer id) {
        userCropService.deleteUserCrop(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
