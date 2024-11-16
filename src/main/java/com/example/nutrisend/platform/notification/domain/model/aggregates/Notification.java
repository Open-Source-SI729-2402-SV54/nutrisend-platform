package com.example.nutrisend.platform.notification.domain.model.aggregates;

import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import jakarta.persistence.*;

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
    private String timestamp;

    @Column(nullable = false)
    private String typeID;

    @Column(nullable = false)
    private Boolean active;

    public Notification() {}

    public Notification(Long userId, String email, String message, String timestamp, String typeID, Boolean active) {}


}
