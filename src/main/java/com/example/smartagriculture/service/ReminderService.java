package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.Reminder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface ReminderService {

    List<Reminder> getAllReminders();

    List<Reminder> getRemindersById(Integer cropId);

    List<Reminder> getRemindersByStatus(String status);

    Reminder addReminder(Reminder reminder);
    List<Reminder> getTodaysReminders();
    Reminder updateReminder(Integer id, Reminder reminderDetails);
    List<Reminder> getTodayReminders(Integer cropId);
    void deleteReminder(Integer id);



}
