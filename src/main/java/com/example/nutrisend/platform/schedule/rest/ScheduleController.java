package com.example.nutrisend.platform.schedule.rest;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleService;
import com.example.nutrisend.platform.schedule.rest.resources.SchedulesResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<SchedulesResource> createSchedule(@RequestBody SchedulesResource resource) {
        CreateScheduleCommand command = new CreateScheduleCommand(resource.userId(), resource.days());
        Schedule createdSchedule = scheduleService.createSchedule(command);
        // Cambiamos aquí para usar un Map<String, Map<String, Long>>
        return new ResponseEntity<>(new SchedulesResource(createdSchedule.getUserId(), convertToNestedMap(createdSchedule.getDays())), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SchedulesResource> getSchedule(@PathVariable Long userId) {
        Optional<Schedule> schedule = scheduleService.getScheduleByUserId(userId);
        return schedule.map(value -> new ResponseEntity<>(new SchedulesResource(value.getUserId(), convertToNestedMap(value.getDays())), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SchedulesResource> updateSchedule(@PathVariable Long userId, @RequestBody SchedulesResource resource) {
        UpdateScheduleCommand command = new UpdateScheduleCommand(userId, resource.days());
        Optional<Schedule> updatedSchedule = scheduleService.updateSchedule(command);
        return updatedSchedule.map(value -> new ResponseEntity<>(new SchedulesResource(value.getUserId(), convertToNestedMap(value.getDays())), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long userId) {
        DeleteScheduleCommand command = new DeleteScheduleCommand(userId);
        boolean deleted = scheduleService.deleteSchedule(command);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Map<String, Map<String, Long>> convertToNestedMap(Map<String, Long> days) {
        Map<String, Map<String, Long>> nestedMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : days.entrySet()) {

            Map<String, Long> innerMap = new HashMap<>();
            innerMap.put("mealId", entry.getValue()); // Cambia "mealId" a la clave que necesites
            nestedMap.put(entry.getKey(), innerMap);
        }
        return nestedMap;
    }
}