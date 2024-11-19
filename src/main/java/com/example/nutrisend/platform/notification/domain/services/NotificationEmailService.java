package com.example.nutrisend.platform.notification.domain.services;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;

public interface NotificationEmailService {
    void sendNotificationEmail(Notification notification);
}
