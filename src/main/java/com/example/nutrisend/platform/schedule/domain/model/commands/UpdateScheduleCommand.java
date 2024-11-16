package com.example.nutrisend.platform.schedule.domain.model.commands;

import java.util.Map;

public record UpdateScheduleCommand(
        Long scheduleId,
        Map<String, CreateDailyMealCommand> weeklyMeals
) {
}
