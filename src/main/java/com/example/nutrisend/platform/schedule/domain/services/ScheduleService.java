package com.example.nutrisend.platform.schedule.domain.services;


import com.example.nutrisend.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.example.nutrisend.platform.meals.jpa.MealRepository;
import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.jpa.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, MealRepository mealRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Schedule createSchedule(CreateScheduleCommand command){
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.userId()));

        var mealMap = mealRepository.findAllById(command.weeklyMeals().keySet())
                .stream()
                .collect(Collectors.toMap(meals -> meals.getId(), meals -> meals));

        Schedule schedule = Schedule.fromCreateCommand(command, user, mealMap);
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public Schedule updateSchedule(Long scheduleId, UpdateScheduleCommand command){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with ID: " + scheduleId));

        var mealMap = mealRepository.findAllById(command.weeklyMeals().keySet())
                .stream()
                .collect(Collectors.toMap(meals -> meals.getId(), meals -> meals));

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(DeleteScheduleCommand command){
        if(!scheduleRepository.existsById(command.scheduleId())){
            throw new IllegalArgumentException("Schedule not found with ID: " + command.scheduleId());
        }
        scheduleRepository.deleteById(command.scheduleId());
    }
}
