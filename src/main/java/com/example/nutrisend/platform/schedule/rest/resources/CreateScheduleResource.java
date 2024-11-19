package com.example.nutrisend.platform.schedule.rest.resources;

import com.example.nutrisend.platform.schedule.domain.model.commands.CreateDailyMealCommand;

import java.util.Map;

public record CreateScheduleResource(
        Long userId,
        Map<Long, DailyMealResource> weeklyMeals) {
}
