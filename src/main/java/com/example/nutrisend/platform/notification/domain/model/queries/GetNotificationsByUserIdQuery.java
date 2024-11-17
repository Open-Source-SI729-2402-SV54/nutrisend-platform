package com.example.nutrisend.platform.notification.domain.model.queries;

public record GetNotificationsByUserIdQuery(Long userId) {
    public GetNotificationsByUserIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or less than or equal to zero");
        }
    }
}
