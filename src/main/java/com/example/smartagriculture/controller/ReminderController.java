package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.Reminder;
import com.example.smartagriculture.service.impl.ReminderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
    public class ReminderController {

    @Autowired
    private ReminderServiceImpl reminderService;

    // 获取所有提醒
    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = reminderService.getAllReminders();
        return ResponseEntity.ok(reminders);
    }

    // 根据ID 获取提醒
    @GetMapping("/{cropId}")
    public ResponseEntity<List<Reminder>> getRemindersById(@PathVariable Integer cropId) {
        List<Reminder> reminders = reminderService.getRemindersById(cropId);
        return ResponseEntity.ok(reminders);
    }

    // 根据状态获取提醒
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reminder>> getRemindersByStatus(@PathVariable String status) {
        List<Reminder> reminders = reminderService.getRemindersByStatus(status);
        System.out.println("reminders: " + reminders);
        return ResponseEntity.ok(reminders);
    }

    // 添加提醒
    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.addReminder(reminder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    // 更新提醒
    @PutMapping("/{cropId}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Integer cropId, @RequestBody Reminder reminderDetails) {
        try {
            Reminder updatedReminder = reminderService.updateReminder(cropId, reminderDetails);
            return ResponseEntity.ok(updatedReminder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 删除提醒
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Integer id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
