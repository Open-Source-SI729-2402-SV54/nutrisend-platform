package com.example.nutrisend.platform.notification.domain.services;

import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
}
