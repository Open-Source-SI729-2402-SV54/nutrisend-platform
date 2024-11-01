package com.example.nutrisend.platform.schedule.rest.resources;

import java.util.Map;

public record SchedulesResource(
        Long userId,
        Map<String, Map<String, Long>> days
){

}
