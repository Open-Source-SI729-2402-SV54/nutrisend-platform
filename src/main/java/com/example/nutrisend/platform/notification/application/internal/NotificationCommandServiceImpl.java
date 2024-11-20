package com.example.nutrisend.platform.notification.application.internal;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.example.nutrisend.platform.notification.domain.model.commands.DeleteNotificationCommand;
import com.example.nutrisend.platform.notification.domain.model.commands.UpdateNotificationCommand;
import com.example.nutrisend.platform.notification.domain.services.NotificationCommandService;
import com.example.nutrisend.platform.notification.infrastructure.persistence.jpa.repositories.EmailService;
import com.example.nutrisend.platform.notification.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final TaskScheduler taskScheduler;

    @Autowired
    public NotificationCommandServiceImpl(
            NotificationRepository notificationRepository,
            EmailService emailService,
            TaskScheduler taskScheduler) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public Long handle(CreateNotificationCommand command) {
        var notification = new Notification(command);
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving category meal: %s".formatted(e.getMessage()));
        }
        return notification.getId();
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        if (!notificationRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Notification with id " + command.id() + " does not exist");
        }
        try {
            notificationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Notification with id " + command.id() + " could not be deleted");
        }
    }

    @Override
    public Optional<Notification> handle(UpdateNotificationCommand command) {
        if(notificationRepository.existsByTypeIdAndIdIsNot(command.typeId(), command.id())) throw new IllegalArgumentException("Notification already exists".formatted(command.id()));
        var result = notificationRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Notification does not exist".formatted(command.id()));
        var notificationToUpdate = result.get();
        try {
            var updatedNotification = notificationRepository.save(notificationToUpdate.updateNotification(command.userId(), command.email(), command.message(), command.typeId(), command.active(), command.timestamp()));
            return Optional.of(updatedNotification);
        } catch (Exception e){
            throw new IllegalArgumentException("Notification could not be updated");
        }
    }

}