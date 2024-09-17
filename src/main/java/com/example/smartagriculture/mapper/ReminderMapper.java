package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.Reminder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReminderMapper {

    // 根据作物 ID 查找提醒
    List<Reminder> findByCropId(@Param("cropId") Integer cropId);

    // 根据提醒状态查找提醒
    List<Reminder> findByStatus(@Param("status") String status);
}
