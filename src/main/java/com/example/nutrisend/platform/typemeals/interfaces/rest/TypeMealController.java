package com.example.nutrisend.platform.typemeals.interfaces.rest;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetTypeMealsByIdQuery;
import com.example.nutrisend.platform.typemeals.domain.services.TypeMealsCommandService;
import com.example.nutrisend.platform.typemeals.domain.services.TypeMealsQueryService;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.CreateTypeMealResource;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.TypeMealResource;
import com.example.nutrisend.platform.typemeals.interfaces.rest.transform.CreateTypeMealCommandFromResourceAssembler;
import com.example.nutrisend.platform.typemeals.interfaces.rest.transform.TypeResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/type-meals", produces = "application/json")
@Tag(name = "Type Meals", description = "Operations related to type meals")
public class TypeMealController {

    private final TypeMealsQueryService typeMealsQueryService;
    private final TypeMealsCommandService typeMealsCommandService;

    public TypeMealController(TypeMealsQueryService typeMealsQueryService, TypeMealsCommandService typeMealsCommandService) {
        this.typeMealsQueryService = typeMealsQueryService;
        this.typeMealsCommandService = typeMealsCommandService;
    }


    // Get /api/v1/type-meals
    @Operation(summary = "Get all types", description = "Retrieve all meal types")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Types retrieved successfully")})
    @GetMapping
    public ResponseEntity<List<TypeMealResource>> getAllTypes() {
        List<TypeMeals> types = typeMealsQueryService.handle(new GetAllTypeMealsQuery());
        List<TypeMealResource> typeResources = types.stream()
                .map(TypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(typeResources);
    }

    // Get /api/v1/type-meals/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get type meal by ID", description = "Retrieve a specific type meal by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type meal found"),
            @ApiResponse(responseCode = "404", description = "Type meal not found")
    })
    public ResponseEntity<TypeMealResource> getTypeMealById(@PathVariable Long id) {
        var getTypeMealByIdQuery = new GetTypeMealsByIdQuery(id);
        var typeMeal = typeMealsQueryService.handle(getTypeMealByIdQuery);
        if (typeMeal.isEmpty()) return ResponseEntity.notFound().build();
        var typeMealEntity = typeMeal.get();
        var typeMealResource = TypeResourceFromEntityAssembler.toResourceFromEntity(typeMealEntity);
        return ResponseEntity.ok(typeMealResource);
    }

    @Operation(summary = "Create a type meal", description = "Create a new type meal with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Type meal created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<TypeMealResource> createTypeMeal(@RequestBody CreateTypeMealResource resource) {
        CreateTypeMealsCommand command = CreateTypeMealCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<TypeMeals> typeMealItem = typeMealsCommandService.handle(command);
        return typeMealItem.map(typeMeal -> new ResponseEntity<>(TypeResourceFromEntityAssembler.toResourceFromEntity(typeMeal), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
