package com.gkfcsolution.rabbitmqapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gkfcsolution.rabbitmqapplication.entity.Notification;
import com.gkfcsolution.rabbitmqapplication.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/notifications")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationRepository notificationRepository;

    @GetMapping("/getallnotification")
    public ResponseEntity<List<Notification>> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        log.info("Fetched {} notifications", notifications.size());
        return  ResponseEntity.ok(notifications);
    }

    @GetMapping("/getallnotificationcount")
    public ResponseEntity<Long> getAllNotificationCount() {
        long count = notificationRepository.count();
        log.info("Total notifications count: {}", count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/getallnotificationsbyrecipient")
    public ResponseEntity<List<Notification>> getAllNotificationByRecipient(@RequestParam String recipient) {
        List<Notification> notifications = notificationRepository.findByRecipientOrderByCreatedAtDesc(recipient);
        log.info("Fetched {} notifications for recipient: {}", notifications.size(), recipient);
        return ResponseEntity.ok(notifications);
    }
    
    
}
