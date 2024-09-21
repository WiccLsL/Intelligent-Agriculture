package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Crop;

import java.util.List;

public interface CropService {

    /**
     * 获取所有作物
     * @return 所有作物的列表
     */
    List<Crop> getAllCrops();

    /**
     * 根据ID获取作物
     * @param id 作物的ID
     * @return 对应ID的作物，若找不到则返回null
     */
    Crop getCropById(Integer id);

    /**
     * 创建新的作物
     * @param crop 要创建的作物对象
     */
    Crop createCrop(Crop crop);

    /**
     * 根据ID删除作物
     * @param id 作物的ID
     */
    void deleteCrop(Integer id);

    /**
     * 更新作物信息
     * @param id 要更新的作物的ID
     * @param newCropData 更新后的作物信息
     */
    void updateCrop(Integer id, Crop newCropData);
}
