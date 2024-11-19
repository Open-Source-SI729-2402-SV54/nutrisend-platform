package com.example.nutrisend.platform.schedule.rest.resources;

import java.util.Map;

public record ScheduleResource(
        Long scheduleId,
        Long userId,
        Map<String, DailyMealResource> weeklyMeals
) {
}
