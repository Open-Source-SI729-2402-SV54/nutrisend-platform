package com.example.nutrisend.platform.notification.domain.model.aggregates;

import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Long typeId;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalTime notificationTime;

    public Notification() {}

    public Notification(Long userId, String email, String message, Long typeId, boolean active, LocalTime notificationTime) {
        this.userId = userId;
        this.email = email;
        this.message = message;
        this.typeId = typeId;
        this.active = active;
        this.notificationTime = notificationTime;
    }


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public Long getTypeId() {
        return typeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalTime notificationTime) {
        this.notificationTime = notificationTime;
    }



}
