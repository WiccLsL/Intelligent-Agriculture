package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.Crop;
import com.example.smartagriculture.entity.UserCrop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCropMapper {

    // 根据用户 ID 查找用户的作物
    List<Crop> findByUserId(@Param("userId") Integer userId);

    // 添加用户作物关系
    int insertUserCrop(UserCrop userCrop);

    // 删除用户作物关系
    int deleteUserCrop(@Param("id") Integer id);

    int deleteUserCropByCropId(@Param("cropId") Integer cropId);
}
