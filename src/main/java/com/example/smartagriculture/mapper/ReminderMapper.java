package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.Reminder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReminderMapper {

    Reminder findById(Integer id);
    List<Reminder> findByCropId(Integer cropId);

    List<Reminder> findByStatus(String status);

    void insert(Reminder reminder);



    void update(Reminder reminder);
    void delete(Integer id);
}
