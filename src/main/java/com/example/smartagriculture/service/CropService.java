package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop getCropById(Long id) {
        return cropRepository.findById(id).orElse(null);
    }

    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }

    public Crop updateCrop(Long id, Crop newCropData) {
        return cropRepository.findById(id).map(crop -> {
            crop.setCropName(newCropData.getCropName());
            crop.setPlantingDate(newCropData.getPlantingDate());
            crop.setExpectedHarvestDate(newCropData.getExpectedHarvestDate());
            return cropRepository.save(crop);
        }).orElse(null);
    }
}

