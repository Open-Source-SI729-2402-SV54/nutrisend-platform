package com.example.nutrisend.platform.schedule.domain.model.commands;

public record CreateDailyMealCommand(
        Long breakfastId,
        Long lunchId,
        Long dinnerId
) {
}
