package com.example.nutrisend.platform.notification.domain.model.queries;

public record GetNotificationsByEmailQuery(String email) {
    public GetNotificationsByEmailQuery {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}