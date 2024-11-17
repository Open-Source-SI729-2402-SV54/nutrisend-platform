package com.example.nutrisend.platform.notification.interfaces.rest.transform;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {

    public static NotificationResource toResourceFromEntity(Notification notification) {
        return new NotificationResource(
                notification.getId(),
                notification.getUserId(),
                notification.getEmail(),
                notification.getMessage(),
                notification.getTypeId(),
                notification.isActive(),
                notification.getNotificationTime()
        );
    }
}