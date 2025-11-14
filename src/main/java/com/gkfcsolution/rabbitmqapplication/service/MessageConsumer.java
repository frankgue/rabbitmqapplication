package com.gkfcsolution.rabbitmqapplication.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.gkfcsolution.rabbitmqapplication.config.RabbitMQConfig;
import com.gkfcsolution.rabbitmqapplication.entity.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues= RabbitMQConfig.EMAIL_NOTIFICATION_QUEUE)
    public void handleEmailNotification(Message message) {
        log.info("Received email notification with Id: {}, Recipient: {}, Subject: {}", message.getId(), message.getRecipient(), message.getSubject());
        // Process the email notification
    }

    @RabbitListener(queues= RabbitMQConfig.SMS_NOTIFICATION_QUEUE)
    public void handleSmsNotification(Message message) {
        log.info("Received SMS notification with Id: {}, Recipient: {}, Content: {}", message.getId(), message.getRecipient(), message.getContent());
        // Process the SMS notification
    }
}
