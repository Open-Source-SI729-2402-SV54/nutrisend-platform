package com.example.nutrisend.platform.schedule.rest.transform;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.rest.resources.SchedulesResource;
public class ScheduleTransformer {
    public static SchedulesResource toResource(Schedule schedule) {
        return new SchedulesResource(schedule.getUserId(), schedule.getDays());
    }

    public static Schedule toEntity(SchedulesResource resource) {
        return new Schedule(resource.userId(), resource.days());
    }
}
