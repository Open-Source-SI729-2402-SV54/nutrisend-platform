package com.example.nutrisend.platform.schedule.rest.transform;

import com.example.nutrisend.platform.schedule.domain.model.commands.CreateDailyMealCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.rest.resources.CreateScheduleResource;

import java.util.Map;
import java.util.stream.Collectors;

public class CreateScheduleResourceFromResourceAssembler {

    public static CreateScheduleCommand toCommand(CreateScheduleResource resource) {
        Map<Long, CreateDailyMealCommand> weeklyMeals = resource.weeklyMeals().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new CreateDailyMealCommand(
                                entry.getValue().breakfast().id(),
                                entry.getValue().lunch().id(),
                                entry.getValue().dinner().id()
                        )
                ));

        return new CreateScheduleCommand(
                resource.userId(),
                weeklyMeals
        );
    }
}
