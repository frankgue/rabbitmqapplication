package com.gkfcsolution.rabbitmqapplication.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.gkfcsolution.rabbitmqapplication.config.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues= RabbitMQConfig.EMAIL_NOTIFICATION_QUEUE)
    public void handleEmailNotification(String message) {
        log.info("Received email notification: {}", message);
        // Process the email notification
    }

    @RabbitListener(queues= RabbitMQConfig.SMS_NOTIFICATION_QUEUE)
    public void handleSmsNotification(String message) {
        log.info("Received SMS notification: {}", message);
        // Process the SMS notification
    }
}
