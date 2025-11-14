package com.gkfcsolution.rabbitmqapplication.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String messageId;
    @Column(nullable=false)
    private String type;
    @Column(nullable=false)
    private String recipient;
    @Column(nullable=false)
    private String subject;
    @Column(columnDefinition="TEXT")
    private String content;
    @Column(nullable=false)
    private String status; // e.g., "SENT", "FAILED", "PENDING"
    @Column(nullable=false)
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (status == null) {
            status = "PENDING";
        }
    }

}
