package com.gkfcsolution.rabbitmqapplication.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.gkfcsolution.rabbitmqapplication.config.RabbitMQConfig;
import com.gkfcsolution.rabbitmqapplication.entity.Message;
import com.gkfcsolution.rabbitmqapplication.entity.Notification;
import com.gkfcsolution.rabbitmqapplication.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    private final NotificationRepository notificationRepository;

    @RabbitListener(queues= RabbitMQConfig.EMAIL_NOTIFICATION_QUEUE)
    public void handleEmailNotification(Message message) {
        log.info("Received email notification with Id: {}, Recipient: {}, Subject: {}", message.getId(), message.getRecipient(), message.getSubject());
        // Process the email notification
        try {
            Thread.sleep(2000);
            Notification notification = Notification.builder()
            .messageId(message.getId())
            .type(message.getType())
            .recipient(message.getRecipient())
            .subject(message.getSubject())
            .content(message.getContent())
            .status("SENT")
            .processedAt(LocalDateTime.now())
            .build();

            notificationRepository.save(notification);
            log.info("Email notification message processed and saved with ID: {} to database", notification.getId());
        } catch (Exception e) {
            log.error("Error processing email notification message with ID: {}", message.getId(), e);
            Notification notification = Notification.builder()
            .messageId(message.getId())
            .type(message.getType())
            .recipient(message.getRecipient())
            .subject(message.getSubject())
            .content(message.getContent())
            .status("FAILED")
            .processedAt(LocalDateTime.now())
            .build();
            notificationRepository.save(notification);
            log.error("Email notification message failed and saved with ID: {} to database", notification.getId());
        }
    }

    @RabbitListener(queues= RabbitMQConfig.SMS_NOTIFICATION_QUEUE)
    public void handleSmsNotification(Message message) {
        log.info("Received SMS notification with Id: {}, Recipient: {}, Content: {}", message.getId(), message.getRecipient(), message.getContent());
        // Process the SMS notification
         try {
            Thread.sleep(500);
            Notification notification = Notification.builder()
            .messageId(message.getId())
            .type(message.getType())
            .recipient(message.getRecipient())
            .subject(message.getSubject())
            .content(message.getContent())
            .status("SENT")
            .processedAt(LocalDateTime.now())
            .build();

            notificationRepository.save(notification);
            log.info("SMS notification message processed and saved with ID: {} to database", notification.getId());
        } catch (Exception e) {
            log.error("Error processing SMS notification message with ID: {}", message.getId(), e);
            Notification notification = Notification.builder()
            .messageId(message.getId())
            .type(message.getType())
            .recipient(message.getRecipient())
            .subject(message.getSubject())
            .content(message.getContent())
            .status("FAILED")
            .processedAt(LocalDateTime.now())
            .build();
            notificationRepository.save(notification);
            log.error("Email notification message failed and saved with ID: {} to database", notification.getId());
        }
    }
}
