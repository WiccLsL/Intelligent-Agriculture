package com.example.smartagriculture.repository;

import com.example.smartagriculture.entity.UserCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCropRepository extends JpaRepository<UserCrop, Long> {
    // 根据用户 ID 查找用户的作物
    List<UserCrop> findByUserId(Long userId);

    // 根据作物 ID 查找用户
    List<UserCrop> findByCropId(Long cropId);
}
