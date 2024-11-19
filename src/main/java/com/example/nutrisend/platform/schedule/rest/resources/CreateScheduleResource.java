package com.example.nutrisend.platform.schedule.rest.resources;

import java.util.Map;

public record CreateScheduleResource(
        Long userId,
        Map<Long, DailyMealResource> weeklyMeals) {
}
