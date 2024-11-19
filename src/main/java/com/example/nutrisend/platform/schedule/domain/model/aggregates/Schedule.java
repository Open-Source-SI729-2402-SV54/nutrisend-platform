package com.example.nutrisend.platform.schedule.domain.model.aggregates;

import com.example.nutrisend.platform.iam.domain.model.aggregates.User;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ElementCollection
    private Map<Long, DailyMeals> weeklyMeals = new HashMap<>();

    public static Schedule fromCreateCommand(CreateScheduleCommand command, User user, Map<Long, Meals> mealsMap) {
        Schedule schedule = new Schedule();
        schedule.setUser(user);

        // Transform command structure to aggregate's internal representation
        command.weeklyMeals().forEach((day, mealCommand) -> {
            DailyMeals dailyMeal = new DailyMeals(
                    mealsMap.get(mealCommand.breakfastId()),
                    mealsMap.get(mealCommand.lunchId()),
                    mealsMap.get(mealCommand.dinnerId())
            );
            schedule.getWeeklyMeals().put(day, dailyMeal);
        });

        return schedule;
    }
}
