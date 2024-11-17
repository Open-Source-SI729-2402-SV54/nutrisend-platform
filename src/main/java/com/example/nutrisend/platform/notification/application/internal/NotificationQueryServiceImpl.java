package com.example.nutrisend.platform.notification.application.internal;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.domain.model.queries.GetAllNotificationsQuery;
import com.example.nutrisend.platform.notification.domain.model.queries.GetNotificationByIdQuery;
import com.example.nutrisend.platform.notification.domain.model.queries.GetNotificationsByUserIdQuery;
import com.example.nutrisend.platform.notification.domain.services.NotificationQueryService;
import com.example.nutrisend.platform.notification.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationsByUserIdQuery query) {
        List<Notification> notifications = notificationRepository.findByUserId(query.userId());
        return notifications.isEmpty() ? Optional.empty() : Optional.of(notifications.get(0));
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }
}
