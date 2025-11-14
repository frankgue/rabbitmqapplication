package com.gkfcsolution.rabbitmqapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkfcsolution.rabbitmqapplication.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
