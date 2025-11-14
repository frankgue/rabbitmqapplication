package com.gkfcsolution.rabbitmqapplication.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableRabbit
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

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(APP_EXCHANGE);
    }

    @Bean
    public Queue emailNotificationQueue() {
        return new Queue(EMAIL_NOTIFICATION_QUEUE, true);
    }

    @Bean
    public Queue smsNotificationQueue() {
        return new Queue(SMS_NOTIFICATION_QUEUE, true);
    }

    @Bean 
    public Binding emailNotificationBinding(TopicExchange exchange, Queue emailNotificationQueue) {
        return BindingBuilder.bind(emailNotificationQueue()).to(exchange()).with(EMAIL_NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Binding smsNotificationBinding(TopicExchange exchange, Queue smsNotificationQueue) {
        return BindingBuilder.bind(smsNotificationQueue()).to(exchange()).with(SMS_NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new  RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
