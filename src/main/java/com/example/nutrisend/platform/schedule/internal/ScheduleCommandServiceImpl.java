package com.example.nutrisend.platform.schedule.internal;


import com.example.nutrisend.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.meals.infrastructure.persistence.jpa.repositories.MealRepository;
import com.example.nutrisend.platform.schedule.domain.model.aggregates.DailyMeals;
import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleCommandService;
import com.example.nutrisend.platform.schedule.jpa.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleCommandServiceImpl  implements ScheduleCommandService {

    private final ScheduleRepository scheduleRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final MealsCommandService mealsCommandService;

    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository, MealRepository mealRepository, UserRepository userRepository, MealsCommandService mealsCommandService) {
        this.scheduleRepository = scheduleRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.mealsCommandService = mealsCommandService;
    }
    @Override
    public Optional<Schedule> handle(CreateScheduleCommand command){
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException(("User not found with ID: " + command.userId())));
        Map<Long, Meals> mealsMap = mealRepository.findAllById(command.getAllMealIds())
                .stream()
                .collect(Collectors.toMap(Meals:: getId, meals -> meals));

        Schedule schedule = Schedule.fromCreateCommand(command, user, mealsMap);
        scheduleRepository.save(schedule);
        return Optional.of(schedule);
    }
    @Override
    public void handle(DeleteScheduleCommand command){
        scheduleRepository.deleteById(command.scheduleId());
    }

    public Optional<Schedule> handle(UpdateScheduleCommand command){
        Schedule schedule = scheduleRepository.findById(command.scheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with ID: " + command.scheduleId()));
        Map<Long, Meals> mealsMap =
                mealRepository.findAllById(command.getAllMealIds())
                .stream()
                .collect(Collectors.toMap(Meals::getId, meals -> meals));
        command.weeklyMeals().forEach((day, dailyMealCommand) -> {
            schedule.getWeeklyMeals().put(String.valueOf(day), new DailyMeals(
                    mealsMap.get(dailyMealCommand.breakfastId()),
                    mealsMap.get(dailyMealCommand.lunchId()),
                    mealsMap.get(dailyMealCommand.dinnerId())
            ));
        });
        scheduleRepository.save(schedule);
        return Optional.of(schedule);
    }
}
