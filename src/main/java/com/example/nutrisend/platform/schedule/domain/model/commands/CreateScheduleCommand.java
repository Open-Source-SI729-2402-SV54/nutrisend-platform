package com.example.nutrisend.platform.schedule.domain.model.commands;



import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record CreateScheduleCommand(
        Long userId,
        Map<Long, CreateDailyMealCommand> weeklyMeals){

    public Set<Long> getAllMealIds(){
        return weeklyMeals.values().stream()
                .flatMap(dailyMealCommand -> Stream.of(
                        dailyMealCommand.breakfastId(),
                        dailyMealCommand.lunchId(),
                        dailyMealCommand.dinnerId()
                ))
                .collect(Collectors.toSet());
    }
}
