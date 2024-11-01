package com.example.nutrisend.platform.schedule.domain.services;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetScheduleQuery;

import java.util.Optional;

public interface ScheduleService {
    Schedule createSchedule(CreateScheduleCommand command);
    Optional<Schedule> updateSchedule(UpdateScheduleCommand command);
    boolean deleteSchedule(DeleteScheduleCommand command);
    Optional<Schedule> getScheduleByUserId(Long userId);
