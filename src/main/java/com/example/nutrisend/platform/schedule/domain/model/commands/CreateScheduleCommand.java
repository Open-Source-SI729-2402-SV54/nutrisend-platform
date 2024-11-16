package com.example.nutrisend.platform.schedule.domain.model.commands;



import java.util.Map;

public record CreateScheduleCommand(
        Long userId,
        Map<String, CreateDailyMealCommand> weeklyMeals){
}
