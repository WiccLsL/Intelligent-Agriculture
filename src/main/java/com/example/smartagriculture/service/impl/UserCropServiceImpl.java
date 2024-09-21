package com.example.smartagriculture.service.impl;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.entity.UserCrop;
import com.example.smartagriculture.mapper.UserCropMapper;
import com.example.smartagriculture.service.UserCropService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserCropServiceImpl implements UserCropService {

    @Autowired
    private UserCropMapper userCropMapper;

    @Override
    public List<Crop> getCropsByUserId(Integer userId) {
        return userCropMapper.findByUserId(userId);
    }

    @Transactional
    @Override
    public void addUserCrop(UserCrop userCrop) {
        System.out.println("Received userCrop: " + userCrop);
        if (userCrop.getCropId() == null || userCrop.getUserId() == null) {
            throw new IllegalArgumentException("Crop or User cannot be null");
        }
        int rows = userCropMapper.insertUserCrop(userCrop);
        if (rows < 1) {
            log.error("用户添加作物失败，添加sql影响行数为: {}", rows);
            throw new RuntimeException("用户添加作物失败");
        }
    }

    @Override
    public void deleteUserCrop(Integer id) {
        int rows = userCropMapper.deleteUserCrop(id);
        if (rows < 1) {
            log.error("用户删除作物失败，添加sql影响行数为: {}", rows);
            throw new RuntimeException("用户删除作物失败");
        }
    }
}
