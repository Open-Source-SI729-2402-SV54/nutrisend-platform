package com.example.nutrisend.platform.schedule.domain.model.commands;

import com.example.nutrisend.platform.user.domain.model.aggregates.Users;

import java.util.Map;

public record UpdateScheduleCommand(Users userId, Map<String,
        Map<String, Long>> days) {

    public UpdateScheduleCommand {
        if (userId == null) {
            throw new IllegalArgumentException("The user id cannot be null");
        }
        if (days == null || days.isEmpty()) {
            throw new IllegalArgumentException("The schedule days cannot be null or empty");
        }
    }
}