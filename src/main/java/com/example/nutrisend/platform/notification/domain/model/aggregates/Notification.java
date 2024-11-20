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
    private String active;

    @Column(nullable = false)
    private String timestamp;

    public Notification() {}

    public Notification(Long userId, String email, String message, Long typeId, String active, String timestamp) {
        this.userId = userId;
        this.email = email;
        this.message = message;
        this.typeId = typeId;
        this.active = active;
        this.timestamp = timestamp;
    }


    public Notification(CreateNotificationCommand command) {
        this.userId = command.userId();
        this.email = command.email();
        this.message = command.message();
        this.typeId = command.typeId();
        this.active = command.active();
        this.timestamp = command.timestamp();
    }

    public Notification updateNotification(Long userId, String email, String message, Long typeId, String active, String timestamp) {
        this.userId = userId;
        this.email = email;
        this.message = message;
        this.typeId = typeId;
        this.active = active;
        this.timestamp = timestamp;
        return this;
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

    public String getActive() { return active;}
    public void setActive(String active) {
        this.active = active;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalTime notificationTime) {
        this.timestamp = timestamp;
    }



}
