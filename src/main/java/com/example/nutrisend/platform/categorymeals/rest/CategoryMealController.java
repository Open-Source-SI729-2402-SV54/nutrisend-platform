package com.example.nutrisend.platform.categorymeals.rest;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetAllCategoryMealsQuery;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetCategoryMealsByIdQuery;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsCommandService;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsQueryService;
import com.example.nutrisend.platform.categorymeals.rest.resources.CategoryMealResource;
import com.example.nutrisend.platform.categorymeals.rest.resources.CreateCategoryMealResource;
import com.example.nutrisend.platform.categorymeals.rest.transform.CategoryResourceFromEntityAssembler;
import com.example.nutrisend.platform.categorymeals.rest.transform.CreateCategoryCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/category", produces = "application/json")
@Tag(name = "Category Meals", description = "Operations related to category meals")
public class CategoryMealController {

    private final CategoryMealsQueryService categoryMealsQueryService;
    private final CategoryMealsCommandService categoryMealsCommandService;



    public CategoryMealController(CategoryMealsQueryService categoryMealsQueryService, CategoryMealsCommandService categoryMealsCommandService) {
        this.categoryMealsQueryService = categoryMealsQueryService;
        this.categoryMealsCommandService = categoryMealsCommandService;
    }



    // Get /api/v1/category
    @Operation(summary = "Get all categories", description = "Retrieve all meal categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Categories retrieved successfully")})
    @GetMapping
    public ResponseEntity<List<CategoryMealResource>> getAllCategories() {
        List<CategoryMeals> categories = categoryMealsQueryService.handle(new GetAllCategoryMealsQuery());
        List<CategoryMealResource> categoryResources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(categoryResources);
    }

    // Get /api/v1/category/{id}
    @Operation(summary = "Get category by ID", description = "Retrieve a specific category by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryMealResource> getCategoryById(@PathVariable Long id) {
        Optional<CategoryMeals> categoryItem = categoryMealsQueryService.handle(new GetCategoryMealsByIdQuery(id));
        return categoryItem.map(category -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Post /api/v1/category
    @Operation(summary = "Create a category meal", description = "Create a new category meal with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category meal created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<CategoryMealResource> createCategoryMeal(@RequestBody CreateCategoryMealResource resource) {
        CreateCategoryMealsCommand command = CreateCategoryCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<CategoryMeals> categoryMealItem = categoryMealsCommandService.handle(command);
        return categoryMealItem.map(categoryMeal -> new ResponseEntity<>(CategoryResourceFromEntityAssembler.toResourceFromEntity(categoryMeal), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
