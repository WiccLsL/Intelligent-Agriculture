package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.Crop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CropMapper {

    /**
     * 获取所有作物
     * @return 作物列表
     */
    List<Crop> getAllCrops();

    /**
     * 根据ID获取作物
     * @param id 作物ID
     * @return 指定ID的作物
     */
    Crop getCropById(Integer id);

    /**
     * 添加新作物
     * @param crop 新作物对象
     */
    void createCrop(Crop crop);

    /**
     * 删除作物
     * @param id 作物ID
     */
    void deleteCrop(Integer id);

    /**
     * 更新作物信息
     * @param crop 包含更新信息的作物对象
     */
    void updateCrop(Crop crop);
}
