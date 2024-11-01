package com.example.nutrisend.platform.meals.rest;
import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.queries.*;
import com.example.nutrisend.platform.meals.domain.services.CategoryMealsQueryService;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.meals.domain.services.MealsQueryService;
import com.example.nutrisend.platform.meals.domain.services.TypeMealsQueryService;
import com.example.nutrisend.platform.meals.rest.resources.CategoryMealResource;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;
import com.example.nutrisend.platform.meals.rest.resources.MealResource;
import com.example.nutrisend.platform.meals.rest.resources.TypeMealResource;
import com.example.nutrisend.platform.meals.rest.transform.CategoryResourceFromEntityAssembler;
import com.example.nutrisend.platform.meals.rest.transform.CreateMealCommandFromResourceAssembler;
import com.example.nutrisend.platform.meals.rest.transform.MealResourceFromEntityAssembler;
import com.example.nutrisend.platform.meals.rest.transform.TypeResourceFromEntityAssembler;
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
    private final CategoryMealsQueryService categoryMealsQueryService;
    private final TypeMealsQueryService typeMealsQueryService;


    public MealController(MealsQueryService mealQueryService, MealsCommandService mealCommandService, CategoryMealsQueryService categoryMealsQueryService, TypeMealsQueryService typeMealsQueryService) {
        this.mealQueryService = mealQueryService;
        this.mealCommandService = mealCommandService;
        this.categoryMealsQueryService = categoryMealsQueryService;
        this.typeMealsQueryService = typeMealsQueryService;
    }

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

    @Operation(summary = "Get all categories", description = "Retrieve all meal categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Categories retrieved successfully")})
    @GetMapping("/category")
    public ResponseEntity<List<CategoryMealResource>> getAllCategories() {
        List<CategoryMeals> categories = categoryMealsQueryService.handle(new GetAllCategoryMealsQuery());
        List<CategoryMealResource> categoryResources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(categoryResources);
    }

    @Operation(summary = "Get all types", description = "Retrieve all meal types")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Types retrieved successfully")})
    @GetMapping("/type-meals")
    public ResponseEntity<List<TypeMealResource>> getAllTypes() {
        List<TypeMeals> types = typeMealsQueryService.handle(new GetAllTypeMealsQuery());
        List<TypeMealResource> typeResources = types.stream()
                .map(TypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(typeResources);
    }

    @Operation(summary = "Get category by ID", description = "Retrieve a specific category by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryMealResource> getCategoryById(@PathVariable String id) {
        Optional<CategoryMeals> categoryItem = categoryMealsQueryService.handle(new GetCategoryMealsByIdQuery(id));
        return categoryItem.map(category -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get type meal by ID", description = "Retrieve a specific type meal by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type meal retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Type meal not found")
    })
    @GetMapping("/type-meals/{id}")
    public ResponseEntity<TypeMealResource> getTypeMealById(@PathVariable String id) {
        Optional<TypeMeals> typeItem = typeMealsQueryService.handle(new GetTypeMealsByIdQuery(id));
        return typeItem.map(type -> ResponseEntity.ok(TypeResourceFromEntityAssembler.toResourceFromEntity(type)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}