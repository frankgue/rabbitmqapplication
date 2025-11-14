package com.gkfcsolution.rabbitmqapplication.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.gkfcsolution.rabbitmqapplication.config.RabbitMQConfig;
import com.gkfcsolution.rabbitmqapplication.entity.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public String sendEmailNotification(String recipient, String subject, String content, String priority) {
        String messageId = java.util.UUID.randomUUID().toString();
        log.info("Producing email notification message: ID={}, recipient={}, subject={}, priority={}",
                messageId, recipient, subject, priority);
        // Logic to send message to RabbitMQ would go here
        Message message = Message.builder()
                .id(messageId)
                .type("EMAIL")
                .recipient(recipient)
                .subject(subject)
                .content(content)
                .priority(priority)
                .timestamp(LocalDateTime.now())
                .build();

                try {
                        log.info("Sending email notification message to RabbitMQ  with ID: {}", messageId);
                        rabbitTemplate.convertAndSend(RabbitMQConfig.APP_EXCHANGE, RabbitMQConfig.EMAIL_NOTIFICATION_ROUTING_KEY, message);
                        log.info("Email notification message sent successfully with ID: {}", messageId);
                        return messageId;
                } catch (Exception e) {
                        log.error("Failed to send email notification message with ID: {}. Error: {}", messageId, e.getMessage());
                        throw new RuntimeException("Failed to send email notification message", e);
                }
                
    }

     public String sendSmsNotification(String recipient, String content) {
        String messageId = java.util.UUID.randomUUID().toString();
        log.info("Producing sms notification message: ID={}, recipient={}",
                messageId, recipient);
        // Logic to send message to RabbitMQ would go here
        Message message = Message.builder()
                .id(messageId)
                .type("SMS")
                .recipient(recipient)
                .content(content)
                .subject("SMS Notification")
                .priority("MEDIUM") // Default priority for SMS
                .timestamp(LocalDateTime.now())
                .build();

                try {
                        log.info("Sending sms notification message to RabbitMQ  with ID: {}", messageId);
                        rabbitTemplate.convertAndSend(RabbitMQConfig.APP_EXCHANGE, RabbitMQConfig.SMS_NOTIFICATION_ROUTING_KEY, message);
                        log.info("Sms notification message sent successfully with ID: {}", messageId);
                        return messageId;
                } catch (Exception e) {
                        log.error("Failed to send sms notification message with ID: {}. Error: {}", messageId, e.getMessage());
                        throw new RuntimeException("Failed to send sms notification message", e);
                }
                
    }

}
