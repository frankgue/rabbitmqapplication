package com.gkfcsolution.rabbitmqapplication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gkfcsolution.rabbitmqapplication.service.MessageProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/messages")
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageProducer messageProducer;

   @PostMapping("/email-notification")
    public ResponseEntity<Map<String, String>> sendEmailNotification(
            @RequestParam String recipient,
            @RequestParam String subject,
            @RequestParam String content,
            @RequestParam(defaultValue = "MEDIUM") String priority
    ) {
        try {
            log.info("Received request to send email to: {}, subject: {}, priority: {}", recipient, subject, priority);
        String messageId = messageProducer.sendEmailNotification(recipient, subject, content, priority);
        return ResponseEntity.ok(Map.of(
            "status", "success",
             "messageId", messageId,
                "message", "Email notification sent to RabbitMQ successfully"
             ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", "Failed to send email notification: " + e.getMessage()
            ));
        }
    }

   @PostMapping("/sms-notification")
    public ResponseEntity<Map<String, String>> sendSmsNotification(
            @RequestParam String recipient,
            @RequestParam String content
    ) {
        try {
            log.info("Received request to send SMS to: {}, content: {}", recipient, content);
        String messageId = messageProducer.sendSmsNotification(recipient, content);
        return ResponseEntity.ok(Map.of(
            "status", "success",
             "messageId", messageId,
                "message", "SMS notification sent to RabbitMQ successfully"
             ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", "Failed to send SMS notification: " + e.getMessage()
            ));
        }
    }
}
