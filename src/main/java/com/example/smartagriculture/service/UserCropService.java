package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.UserCrop;
import com.example.smartagriculture.repository.UserCropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCropService {

    @Autowired
    private UserCropRepository userCropRepository;

    // 获取所有的用户作物关系
    public List<UserCrop> getAllUserCrops() {
        return userCropRepository.findAll();
    }

    // 通过用户 ID 获取用户的作物
    public List<UserCrop> getCropsByUserId(Long userId) {
        return userCropRepository.findByUserId(userId);
    }

    // 通过作物 ID 获取作物的用户
    public List<UserCrop> getUsersByCropId(Long cropId) {
        return userCropRepository.findByCropId(cropId);
    }

    // 添加用户作物关系
    public UserCrop addUserCrop(UserCrop userCrop) {
        System.out.println("Received userCrop: " + userCrop);
        if (userCrop.getCrop() == null || userCrop.getUser() == null) {
            throw new IllegalArgumentException("Crop or User cannot be null");
        }
        return userCropRepository.save(userCrop);
    }


    // 更新用户作物关系
    public UserCrop updateUserCrop(Long id, UserCrop userCropDetails) {
        Optional<UserCrop> existingUserCrop = userCropRepository.findById(id);
        if (existingUserCrop.isPresent()) {
            UserCrop userCrop = existingUserCrop.get();
            userCrop.setUser(userCropDetails.getUser());
            userCrop.setCrop(userCropDetails.getCrop());
            return userCropRepository.save(userCrop);
        } else {
            throw new RuntimeException("UserCrop with id " + id + " not found");
        }
    }

    // 删除用户作物关系
    public void deleteUserCrop(Long id) {
        userCropRepository.deleteById(id);
    }
}
