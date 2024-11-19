package com.example.nutrisend.platform.notification.domain.services;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.domain.model.queries.GetAllNotificationsQuery;
import com.example.nutrisend.platform.notification.domain.model.queries.GetNotificationByIdQuery;
import com.example.nutrisend.platform.notification.domain.model.queries.GetNotificationsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    Optional<Notification> handle(GetNotificationsByUserIdQuery query);
    List<Notification> handle(GetAllNotificationsQuery query);

    Optional<Notification> handle(GetNotificationByIdQuery query);
}
