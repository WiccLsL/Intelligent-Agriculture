package com.example.smartagriculture.service.impl;

import com.example.smartagriculture.entity.Reminder;
import com.example.smartagriculture.mapper.ReminderMapper;
import com.example.smartagriculture.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderMapper reminderMapper;


    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<Reminder> reminders = reminderMapper.findByStatus("Pending"); // Assuming status is "Pending" for checking

        for (Reminder reminder : reminders) {
            if (shouldTriggerReminder(reminder, now)) {
                triggerReminder(reminder);
            }
        }
    }

    private boolean shouldTriggerReminder(Reminder reminder, LocalDateTime now) {
        LocalDateTime reminderDate = reminder.getReminderDate();
        if (reminderDate == null) {
            // 记录日志以帮助排查问题
            System.err.println("Reminder date is null for reminder ID: " + reminder.getId());
            return false;
        }
        return reminderDate.isBefore(now);
    }


    private void triggerReminder(Reminder reminder) {
        // 实现触发提醒的逻辑
        System.out.println("Reminder triggered: " + reminder.getReminderMessage());
    }


    @Override
    public List<Reminder> getAllReminders() {
        // Add method for fetching all reminders if needed
        return reminderMapper.findByStatus("All"); // Example implementation
    }

    @Override
    public List<Reminder> getRemindersById(Integer cropId) {
        return reminderMapper.findByCropId(cropId);
    }

    @Override
    public List<Reminder> getRemindersByStatus(String status) {
        return reminderMapper.findByStatus(status);
    }

    @Override
    public Reminder addReminder(Reminder reminder) {
        reminder.setStatus("Pending"); // 设置默认状态为待办
        reminder.setReminderDate(LocalDateTime.now()); // 设置默认提醒时间为当前时间
        reminderMapper.insert(reminder); // 保存提醒
        return reminder;
    }

    @Override
    public Reminder updateReminder(Integer cropId, Reminder reminderDetails) {
        Reminder existingReminder = (Reminder) reminderMapper.findByCropId(cropId); // 假设有一个根据 ID 查找提醒的方法
        if (existingReminder != null) {
            existingReminder.setReminderMessage(reminderDetails.getReminderMessage());
            existingReminder.setReminderDate(reminderDetails.getReminderDate());
            existingReminder.setStatus(reminderDetails.getStatus());
            reminderMapper.update(existingReminder); // 传递整个 Reminder 对象
            return existingReminder;
        } else {
            throw new RuntimeException("Reminder with id " + cropId + " not found");
        }
    }

    @Override
    public void deleteReminder(Integer id) {
        reminderMapper.delete(id); // 删除提醒
    }
}