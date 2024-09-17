package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Reminder;

import java.util.List;

public interface ReminderService {

    List<Reminder> getAllReminders();

    List<Reminder> getRemindersByCropId(Integer cropId);

    List<Reminder> getRemindersByStatus(String status);

    Reminder addReminder(Reminder reminder);

    Reminder updateReminder(Integer id, Reminder reminderDetails);

    void deleteReminder(Integer id);

    // 可选：定义用于调度的接口方法（如果需要）
    void checkReminders();
}
