package com.example.nutrisend.platform.schedule.application.internal;


import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetAllSchedulesQuery;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetScheduleByIdQuery;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleQueryService;
import com.example.nutrisend.platform.schedule.jpa.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesQuery query) {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        return scheduleRepository.findById(query.scheduleId());
    }
}
