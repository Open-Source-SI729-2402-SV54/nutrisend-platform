package com.example.nutrisend.platform.notification.domain.model.queries;

public record GetNotificationByIdQuery(Long id) {
    public GetNotificationByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to zero");
        }
    }
}