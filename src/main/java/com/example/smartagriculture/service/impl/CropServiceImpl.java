package com.example.smartagriculture.service.impl;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.mapper.CropMapper;
import com.example.smartagriculture.mapper.UserCropMapper;
import com.example.smartagriculture.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropMapper cropMapper;

    @Autowired
    private UserCropMapper userCropMapper;

    public List<Crop> getAllCrops() {
        return cropMapper.getAllCrops();
    }

    public Crop getCropById(Integer id) {
        return cropMapper.getCropById(id);
    }

    public void createCrop(Crop crop) {
        cropMapper.createCrop(crop);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCrop(Integer id) {
        cropMapper.deleteCrop(id);
        userCropMapper.deleteUserCropByCropId(id);
    }

    public void updateCrop(Integer id, Crop newCropData) {
        Crop crop = cropMapper.getCropById(id);
        if (crop != null) {
            crop.setCropName(newCropData.getCropName());
            crop.setPlantingDate(newCropData.getPlantingDate());
            crop.setExpectedHarvestDate(newCropData.getExpectedHarvestDate());
            cropMapper.updateCrop(crop);
        }
    }
}
