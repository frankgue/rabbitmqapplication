package com.gkfcsolution.rabbitmqapplication.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Exchange
    public static final String APP_EXCHANGE = "app.exchange";
    // Routing Key
    public static final String EMAIL_NOTIFICATION_ROUTING_KEY = "notification.email.routingkey";
}
