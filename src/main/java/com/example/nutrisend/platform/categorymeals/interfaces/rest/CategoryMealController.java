package com.example.nutrisend.platform.categorymeals.interfaces.rest;

import com.example.nutrisend.platform.categorymeals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetAllCategoryMealsQuery;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetCategoryMealsByIdQuery;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsCommandService;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsQueryService;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.resources.CategoryMealResource;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.resources.CreateCategoryMealResource;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.resources.UpdateCategoryResource;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.transform.CreateCategoryResourceFromResourceAssembler;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.transform.UpdateCategoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category", produces = "application/json")
@Tag(name = "Category Meals", description = "Available Category Meals Endpoints")
public class CategoryMealController {
    private final CategoryMealsQueryService categoryMealsQueryService;
    private final CategoryMealsCommandService categoryMealsCommandService;

    public CategoryMealController(CategoryMealsQueryService categoryMealsQueryService, CategoryMealsCommandService categoryMealsCommandService) {
        this.categoryMealsQueryService = categoryMealsQueryService;
        this.categoryMealsCommandService = categoryMealsCommandService;
    }

    // Get /api/v1/category
    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all categories")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Categories found"),
            @ApiResponse(responseCode = "404", description = "Categories not found")
    })
    public ResponseEntity<List<CategoryMealResource>> getAllCategories() {
        var categories = categoryMealsQueryService.handle(new GetAllCategoryMealsQuery());
        if (categories.isEmpty()) return ResponseEntity.notFound().build();
        var categoryResources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(categoryResources);
    }

    // Get /api/v1/category/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get Category Meal by id", description = "Get Category meal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category meañ found"),
            @ApiResponse(responseCode = "404", description = "Category meal not found")
    })
    public ResponseEntity<CategoryMealResource> getCategoryById(@PathVariable("id") Long id) {
        var getCategoryByIdQuery = new GetCategoryMealsByIdQuery(id);
        var category = categoryMealsQueryService.handle(getCategoryByIdQuery);
        if (category.isEmpty()) return ResponseEntity.notFound().build();
        var categoryEntity = category.get();
        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(categoryEntity);
        return ResponseEntity.ok(categoryResource);
    }

    // Post /api/v1/category
    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryMealResource> createCategoryMeal(@RequestBody CreateCategoryMealResource resource) {
        var createCategoryCommand = CreateCategoryResourceFromResourceAssembler.toCommandFromResource(resource);
        var categoryId = categoryMealsCommandService.handle(createCategoryCommand);
        if (categoryId == null || categoryId == 0L) return ResponseEntity.badRequest().build();
        var getCategoryByIdQuery = new GetCategoryMealsByIdQuery(categoryId);
        var category = categoryMealsQueryService.handle(getCategoryByIdQuery);
        if (category.isEmpty()) return ResponseEntity.notFound().build();
        var categoryEntity = category.get();
        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(categoryEntity);
        return new ResponseEntity<>(categoryResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category meal", description = "Update category meal")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Category meal updated"),
            @ApiResponse(responseCode = "404", description = "Category meal not found")
    })
    public ResponseEntity<CategoryMealResource> updateCategoryMeal(@PathVariable("id") Long id,
                                                                   @RequestBody UpdateCategoryResource resource) {
        var updateCategoryCommand = UpdateCategoryCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updatedCategory = categoryMealsCommandService.handle(updateCategoryCommand);
        if (updatedCategory.isEmpty()) return ResponseEntity.notFound().build();
        var updatedCategoryEntity = updatedCategory.get();
        var updatedCategoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(updatedCategoryEntity);
        return ResponseEntity.ok(updatedCategoryResource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category meal", description = "Delete category meal")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Category meal deleted"),
            @ApiResponse(responseCode = "404", description = "Category meal not found")
    })
    public ResponseEntity<?> deleteCategoryMeal(@PathVariable("id") Long id) {
        var deleteCategoryCommand = new DeleteCategoryMealsCommand(id);
        categoryMealsCommandService.handle(deleteCategoryCommand);
        return ResponseEntity.ok("Deleted course");
    }
}
