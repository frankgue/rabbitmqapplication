package com.gkfcsolution.rabbitmqapplication.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Exchange
    public static final String APP_EXCHANGE = "app.exchange";

    // Routing EMAIL Key
    public static final String EMAIL_NOTIFICATION_ROUTING_KEY = "notification.email.routingkey";
    // Routing SMS Key
    public static final String SMS_NOTIFICATION_ROUTING_KEY = "notification.sms.routingkey";

    // Queue EMAIL Key
    public static final String EMAIL_NOTIFICATION_QUEUE = "notification.email.queue";
    // Queue SMS Key
    public static final String SMS_NOTIFICATION_QUEUE = "notification.sms.queue";
}
