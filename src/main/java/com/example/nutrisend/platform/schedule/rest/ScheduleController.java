package com.example.nutrisend.platform.schedule.rest;



import com.example.nutrisend.platform.meals.interfaces.rest.resources.MealResource;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetAllSchedulesQuery;
import com.example.nutrisend.platform.schedule.domain.model.queries.GetScheduleByIdQuery;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleCommandService;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleQueryService;
import com.example.nutrisend.platform.schedule.rest.resources.CreateScheduleResource;
import com.example.nutrisend.platform.schedule.rest.resources.ScheduleResource;
import com.example.nutrisend.platform.schedule.rest.transform.CreateScheduleResourceFromResourceAssembler;
import com.example.nutrisend.platform.schedule.rest.transform.ScheduleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/schedules", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Schedules", description = "Available Schedules Endpoints")
public class ScheduleController {

    private final ScheduleQueryService scheduleQueryService;
    private final ScheduleCommandService scheduleCommandService;

    public ScheduleController(ScheduleQueryService scheduleQueryService, ScheduleCommandService scheduleCommandService) {
        this.scheduleQueryService = scheduleQueryService;
        this.scheduleCommandService = scheduleCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new schedule", description = "Create a new schedule for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Schedule created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ScheduleResource> createSchedule(@RequestBody CreateScheduleResource resource) {
        var createScheduleCommand = CreateScheduleResourceFromResourceAssembler.toCommand(resource);
        var scheduleOptional = scheduleCommandService.handle(createScheduleCommand);

        if (scheduleOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var schedule = scheduleOptional.get();
        // Map the schedule entity to a resource
        // Assuming mealResourceMap is provided from another service or cache
        Map<Long, MealResource> mealResourceMap = Map.of(); // Placeholder, replace with actual logic
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResource(schedule, mealResourceMap);

        return new ResponseEntity<>(scheduleResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all schedules", description = "Retrieve all schedules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedules retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No schedules found")
    })
    public ResponseEntity<List<ScheduleResource>> getAllSchedules() {
        var schedules = scheduleQueryService.handle(new GetAllSchedulesQuery());
        if (schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<Long, MealResource> mealResourceMap = Map.of(); // Placeholder, replace with actual logic
        var scheduleResources = schedules.stream()
                .map(schedule -> ScheduleResourceFromEntityAssembler.toResource(schedule, mealResourceMap))
                .toList();

        return ResponseEntity.ok(scheduleResources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID", description = "Retrieve a schedule by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    public ResponseEntity<ScheduleResource> getScheduleById(@PathVariable("id") Long id) {
        var getScheduleByIdQuery = new GetScheduleByIdQuery(id);
        var scheduleOptional = scheduleQueryService.handle(getScheduleByIdQuery);

        if (scheduleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var schedule = scheduleOptional.get();
        Map<Long, MealResource> mealResourceMap = Map.of(); // Placeholder, replace with actual logic
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResource(schedule, mealResourceMap);

        return ResponseEntity.ok(scheduleResource);
    }
}
























