package com.example.nutrisend.platform.availability.interfaces.rest;

import com.example.nutrisend.platform.availability.domain.model.queries.GetAllAvailabilityQuery;
import com.example.nutrisend.platform.availability.domain.services.AvailabilityQueryService;
import com.example.nutrisend.platform.availability.interfaces.rest.resources.AvailabilityResource;
import com.example.nutrisend.platform.availability.interfaces.rest.transform.AvailabilityResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/availability",  produces = APPLICATION_JSON_VALUE)
@Tag(name = "Availability", description = "Available Endpoints")
public class AvailabilityController {

    private final AvailabilityQueryService availabilityQueryService;

    public AvailabilityController(AvailabilityQueryService availabilityQueryService) {
        this.availabilityQueryService = availabilityQueryService;
    }
    @GetMapping
    @Operation(summary = "Get all availability", description = "Get all Availability")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Availability found"),
            @ApiResponse(responseCode = "404", description = "Availability not found")
    })
    public ResponseEntity<List<AvailabilityResource>> getAllAvailability() {
        var availability = availabilityQueryService.handle(new GetAllAvailabilityQuery());
        if (availability.isEmpty()) return ResponseEntity.notFound().build();
        var availabilityResources = availability.stream()
                .map(AvailabilityResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(availabilityResources);
    }
}
