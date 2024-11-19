package com.example.nutrisend.platform.notification.domain.services;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.example.nutrisend.platform.notification.domain.model.commands.DeleteNotificationCommand;
import com.example.nutrisend.platform.notification.domain.model.commands.UpdateNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
    Optional<Notification> handle(UpdateNotificationCommand command);
}
