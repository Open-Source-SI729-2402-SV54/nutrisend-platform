package com.example.nutrisend.platform.meals.rest;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.meals.domain.services.MealsQueryService;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;
import com.example.nutrisend.platform.meals.rest.resources.MealResource;
import com.example.nutrisend.platform.meals.rest.transform.CreateMealCommandFromResourceAssembler;
import com.example.nutrisend.platform.meals.rest.transform.MealResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/meals", produces = "application/json")
@Tag(name = "Meals", description = "Operations related to meals")
public class MealController {
    private final MealsQueryService mealQueryService;
    private final MealsCommandService mealCommandService;

    public MealController(MealsQueryService mealQueryService, MealsCommandService mealCommandService) {
        this.mealQueryService = mealQueryService;
        this.mealCommandService = mealCommandService;
    }

    // Post /api/v1/meals
    @Operation(summary = "Create a meal", description = "Create a new meal with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<MealResource> createMeal(@RequestBody CreateMealResource resource) {
        CreateMealsCommand command = CreateMealCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Meals> mealItem = mealCommandService.handle(command);
        return mealItem.map(meal -> new ResponseEntity<>(MealResourceFromEntityAssembler.toResourceFromEntity(meal), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Get /api/v1/meals
    @Operation(
            summary = "Get all meals",
            description = "Retrieve all meals"
    )
    @GetMapping
    public ResponseEntity<List<MealResource>> getAllMeals() {
        List<Meals> meals = mealQueryService.handle(new GetAllMealsQuery());
        List<MealResource> mealResources = meals.stream()
                .map(MealResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(mealResources);
    }

    // Get /api/v1/meals/{id}
    @Operation(
            summary = "Get meal by ID",
            description = "Retrieve a specific meal by its unique ID"
    )
    @GetMapping("{id}")
    public ResponseEntity<MealResource> getMealById(@PathVariable String id) {
        Optional<Meals> mealItem = mealQueryService.handle(new GetMealsByIdQuery(id));
        return mealItem.map(meal -> ResponseEntity.ok(MealResourceFromEntityAssembler.toResourceFromEntity(meal)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}