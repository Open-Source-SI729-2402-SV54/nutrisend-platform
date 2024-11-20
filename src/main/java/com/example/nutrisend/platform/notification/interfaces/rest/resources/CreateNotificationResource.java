package com.example.nutrisend.platform.notification.interfaces.rest.resources;

import java.time.LocalTime;

public record CreateNotificationResource(
        Long userId,
        String email,
        String message,
        Long typeId,
        boolean active,
        LocalTime notificationTime
) {
}