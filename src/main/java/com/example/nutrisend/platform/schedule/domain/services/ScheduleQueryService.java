package com.example.nutrisend.platform.schedule.domain.services;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetAllSchedulesQuery;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetScheduleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {

    List<Schedule> handle(GetAllSchedulesQuery query);
    Optional<Schedule> handle(GetScheduleByIdQuery query);
}
