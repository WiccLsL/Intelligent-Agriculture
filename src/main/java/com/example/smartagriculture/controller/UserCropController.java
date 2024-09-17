package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.UserCrop;
import com.example.smartagriculture.service.UserCropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-crops")
public class UserCropController {

    @Autowired
    private UserCropService userCropService;

    // 获取所有的用户作物关系
    @GetMapping
    public ResponseEntity<List<UserCrop>> getAllUserCrops() {
        List<UserCrop> userCrops = userCropService.getAllUserCrops();
        return ResponseEntity.ok(userCrops);
    }

    // 通过用户 ID 获取该用户的所有作物
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCrop>> getCropsByUserId(@PathVariable Long userId) {
        List<UserCrop> userCrops = userCropService.getCropsByUserId(userId);
        return ResponseEntity.ok(userCrops);
    }

    // 通过作物 ID 获取所有拥有该作物的用户
    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<UserCrop>> getUsersByCropId(@PathVariable Long cropId) {
        List<UserCrop> userCrops = userCropService.getUsersByCropId(cropId);
        return ResponseEntity.ok(userCrops);
    }

    // 添加用户作物关系
    @PostMapping
    public ResponseEntity<?> createUserCrop(@RequestBody UserCrop userCrop) {
        try {
            if (userCrop.getCrop() == null || userCrop.getUser() == null) {
                return ResponseEntity.badRequest().body("Crop or User cannot be null");
            }
            UserCrop createdUserCrop = userCropService.addUserCrop(userCrop);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserCrop);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 更新用户作物关系
    @PutMapping("/{id}")
    public ResponseEntity<UserCrop> updateUserCrop(@PathVariable Long id, @RequestBody UserCrop userCropDetails) {
        try {
            UserCrop updatedUserCrop = userCropService.updateUserCrop(id, userCropDetails);
            return ResponseEntity.ok(updatedUserCrop);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 删除用户作物关系
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCrop(@PathVariable Long id) {
        userCropService.deleteUserCrop(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
