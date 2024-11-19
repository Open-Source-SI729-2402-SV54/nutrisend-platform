package com.example.nutrisend.platform.notification.domain.model.commands;

public record DeleteNotificationCommand(Long id) {
    public DeleteNotificationCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
    }
}