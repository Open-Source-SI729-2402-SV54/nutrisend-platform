package com.example.nutrisend.platform.schedule.rest.transform;

import com.example.nutrisend.platform.meals.interfaces.rest.resources.MealResource;
import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.rest.resources.DailyMealResource;
import com.example.nutrisend.platform.schedule.rest.resources.ScheduleResource;

import java.util.Map;
import java.util.stream.Collectors;

public class ScheduleResourceFromEntityAssembler {
    public static ScheduleResource toResource(Schedule schedule, Map<Long, MealResource> mealResourceMap) {
        Map<Long, DailyMealResource> weeklyMeals = schedule.getWeeklyMeals().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // El día como clave (String)
                        entry -> {
                            // Transformar cada `DailyMeals` en `DailyMealResource`
                            var dailyMeals = entry.getValue();
                            return new DailyMealResource(
                                    mealResourceMap.get(dailyMeals.getBreakfast().getId()),
                                    mealResourceMap.get(dailyMeals.getLunch().getId()),
                                    mealResourceMap.get(dailyMeals.getDinner().getId())
                            );
                        }
                ));

        return new ScheduleResource(
                schedule.getId(),
                schedule.getUser().getId(),
                weeklyMeals
        );
    }
}
