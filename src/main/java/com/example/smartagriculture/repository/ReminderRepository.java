package com.example.smartagriculture.repository;

import com.example.smartagriculture.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    // 根据作物 ID 查找提醒
    List<Reminder> findByCropId(Long cropId);

    // 根据提醒状态查找提醒
    List<Reminder> findByStatus(String status);
}
