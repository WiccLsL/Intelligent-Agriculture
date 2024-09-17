package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Reminder;
import com.example.smartagriculture.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;
    // 每分钟检查一次提醒
    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<Reminder> reminders = reminderRepository.findAll();

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
    // 获取所有提醒
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    // 根据作物 ID 获取提醒
    public List<Reminder> getRemindersByCropId(Long cropId) {
        return reminderRepository.findByCropId(cropId);
    }

    // 根据状态获取提醒
    public List<Reminder> getRemindersByStatus(String status) {
        return reminderRepository.findByStatus(status);
    }

    // 添加提醒
    public Reminder addReminder(Reminder reminder) {
        reminder.setStatus("Pending"); // 设置默认状态为待办
        reminder.setReminderDate(LocalDateTime.now()); // 设置默认提醒时间为当前时间
        return reminderRepository.save(reminder);
    }

    // 更新提醒
    public Reminder updateReminder(Long id, Reminder reminderDetails) {
        Optional<Reminder> existingReminder = reminderRepository.findById(id);
        if (existingReminder.isPresent()) {
            Reminder reminder = existingReminder.get();
            reminder.setReminderMessage(reminderDetails.getReminderMessage());
            reminder.setReminderDate(reminderDetails.getReminderDate());
            reminder.setStatus(reminderDetails.getStatus());
            return reminderRepository.save(reminder);
        } else {
            throw new RuntimeException("Reminder with id " + id + " not found");
        }
    }

    // 删除提醒
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
}
