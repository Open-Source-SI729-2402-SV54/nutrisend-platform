package com.example.nutrisend.platform.schedule.domain.model.commands;

import com.example.nutrisend.platform.user.domain.model.aggregates.Users;

public record DeleteScheduleCommand(Long userId) {
    public DeleteScheduleCommand {
        if (userId == null) {
            throw new IllegalArgumentException("The user id cannot be null");
        }
    }
}