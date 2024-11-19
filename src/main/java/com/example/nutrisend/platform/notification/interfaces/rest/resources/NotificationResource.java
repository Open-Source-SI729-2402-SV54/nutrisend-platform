package com.example.nutrisend.platform.notification.interfaces.rest.resources;

import java.time.LocalTime;

public record NotificationResource(
        Long id,
        Long userId,
        String email,
        String message,
        Long typeId,
        boolean active,
        LocalTime notificationTime
) {}
