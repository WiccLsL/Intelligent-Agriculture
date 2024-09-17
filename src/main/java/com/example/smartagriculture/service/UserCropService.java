package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.entity.UserCrop;

import java.util.List;

public interface UserCropService {

    List<Crop> getCropsByUserId(Integer userId);

    void addUserCrop(UserCrop userCrop);

    void deleteUserCrop(Integer id);
}
