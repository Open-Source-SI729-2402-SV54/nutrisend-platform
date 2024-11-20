package com.example.nutrisend.platform.notification.domain.model.commands;

import java.time.LocalTime;

public record CreateNotificationCommand(
        Long userId,
        String email,
        String message,
        Long typeId,
        boolean active,
        LocalTime notificationTime
) {
    public CreateNotificationCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or less than or equal to zero.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank.");
        }
        if (typeId == null || typeId <= 0) {
            throw new IllegalArgumentException("Type ID cannot be null or less than or equal to zero.");
        }
        if (notificationTime == null) {
            throw new IllegalArgumentException("Notification time cannot be null.");
        }
    }
}