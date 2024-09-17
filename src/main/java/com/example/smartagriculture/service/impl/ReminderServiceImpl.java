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

    @Override
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
        // 根据提醒的类型和时间，判断是否应该触发提醒
        // 这里只是一个示例，具体实现根据需求而定
        return reminder.getReminderDate().isBefore(now);
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
    public List<Reminder> getRemindersByCropId(Integer cropId) {
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
        // Add save implementation here if needed
        return reminder;
    }

    @Override
    public Reminder updateReminder(Integer id, Reminder reminderDetails) {
        Optional<Reminder> existingReminder = Optional.ofNullable(reminderMapper.findByStatus("Pending").stream()
                .filter(reminder -> reminder.getId().equals(id))
                .findFirst()
                .orElse(null));
        if (existingReminder.isPresent()) {
            Reminder reminder = existingReminder.get();
            reminder.setReminderMessage(reminderDetails.getReminderMessage());
            reminder.setReminderDate(reminderDetails.getReminderDate());
            reminder.setStatus(reminderDetails.getStatus());
            // Add update implementation here if needed
            return reminder;
        } else {
            throw new RuntimeException("Reminder with id " + id + " not found");
        }
    }

    @Override
    public void deleteReminder(Integer id) {
        // Add delete implementation here if needed
    }
}
