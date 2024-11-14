package com.example.nutrisend.platform.meals.interfaces.rest;

import com.example.nutrisend.platform.meals.domain.model.queries.GetAllMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.meals.domain.services.MealsQueryService;
import com.example.nutrisend.platform.meals.interfaces.rest.resources.CreateMealResource;
import com.example.nutrisend.platform.meals.interfaces.rest.resources.MealResource;
import com.example.nutrisend.platform.meals.interfaces.rest.transform.CreateMealResourceFromResourceAssembler;
import com.example.nutrisend.platform.meals.interfaces.rest.transform.MealResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/meals", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Meals", description = "Available Meals Endpoints")
public class MealController {
    private final MealsQueryService mealQueryService;
    private final MealsCommandService mealCommandService;

    public MealController(MealsQueryService mealQueryService, MealsCommandService mealCommandService) {
        this.mealQueryService = mealQueryService;
        this.mealCommandService = mealCommandService;
    }

    // Post /api/v1/meals
    @PostMapping
    @Operation(summary = "Create a new meal", description = "Create a new meal")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Meal created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    public ResponseEntity<MealResource> createMeal(@RequestBody CreateMealResource resource) {
        var createMealCommand = CreateMealResourceFromResourceAssembler.toCommandFromResource(resource);
        var mealId = mealCommandService.handle(createMealCommand);
        if (mealId == null || mealId == 0L) return ResponseEntity.badRequest().build();
        var getMealByIdQuery = new GetMealsByIdQuery(mealId);
        var meal = mealQueryService.handle(getMealByIdQuery);
        if (meal.isEmpty()) return ResponseEntity.badRequest().build();
        var mealEntity = meal.get();
        var mealResource = MealResourceFromEntityAssembler.toResourceFromEntity(mealEntity);
        return new ResponseEntity<>(mealResource, HttpStatus.CREATED);
    }

    // Get /api/v1/meals
    @GetMapping
    @Operation(summary = "Get all meals", description = "Get all meals")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Meals found"),
            @ApiResponse(responseCode = "404", description = "Meals not found")
    })
    public ResponseEntity<List<MealResource>> getAllMeals() {
        var meal = mealQueryService.handle(new GetAllMealsQuery());
        if (meal.isEmpty()) return ResponseEntity.notFound().build();
        var mealResources = meal.stream()
                .map(MealResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(mealResources);
    }

    // Get /api/v1/meals/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get meal by id", description = "Get meal by id")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Meal found"),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    public ResponseEntity<MealResource> getMealById(@PathVariable("id") Long id) {
        var getMealByIdQuery = new GetMealsByIdQuery(id);
        var meal = mealQueryService.handle(getMealByIdQuery);
        if (meal.isEmpty()) return ResponseEntity.notFound().build();
        var mealEntity = meal.get();
        var mealResource = MealResourceFromEntityAssembler.toResourceFromEntity(mealEntity);
        return ResponseEntity.ok(mealResource);
    }
}