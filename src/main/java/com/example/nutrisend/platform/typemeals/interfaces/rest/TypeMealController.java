package com.example.nutrisend.platform.typemeals.interfaces.rest;

import com.example.nutrisend.platform.typemeals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetTypeMealsByIdQuery;
import com.example.nutrisend.platform.typemeals.domain.services.TypeMealsCommandService;
import com.example.nutrisend.platform.typemeals.domain.services.TypeMealsQueryService;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.CreateTypeMealResource;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.TypeMealResource;
import com.example.nutrisend.platform.typemeals.interfaces.rest.transform.CreateTypeMealResourceFromResourceAssembler;
import com.example.nutrisend.platform.typemeals.interfaces.rest.transform.TypeResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/type-meals", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Type Meals", description = "Available Type Meals Endpoints")
public class TypeMealController {

    private final TypeMealsQueryService typeMealsQueryService;
    private final TypeMealsCommandService typeMealsCommandService;

    public TypeMealController(TypeMealsQueryService typeMealsQueryService, TypeMealsCommandService typeMealsCommandService) {
        this.typeMealsQueryService = typeMealsQueryService;
        this.typeMealsCommandService = typeMealsCommandService;
    }

    // Get /api/v1/type-meals
    @GetMapping
    @Operation(summary = "Get all types", description = "Get all meal types")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Type meals found"),
            @ApiResponse(responseCode = "404", description = "Type meals not found")
    })
    public ResponseEntity<List<TypeMealResource>> getAllTypes() {
        var type = typeMealsQueryService.handle(new GetAllTypeMealsQuery());
        if (type.isEmpty()) return ResponseEntity.notFound().build();
        var typeResources = type.stream()
                .map(TypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(typeResources);
    }

    // Get /api/v1/type-meals/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get Type Meal by id", description = "Get type of meal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Type meal found"),
            @ApiResponse(responseCode = "404", description = "Type meal not found")
    })
    public ResponseEntity<TypeMealResource> getTypeMealById(@PathVariable("id") Long id) {
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
        var createTypeCommand = CreateTypeMealResourceFromResourceAssembler.toCommandFromResource(resource);
        var typeId = typeMealsCommandService.handle(createTypeCommand);
        if (typeId == null || typeId == 0L) return ResponseEntity.badRequest().build();
        var getTypeByIdQuery = new GetTypeMealsByIdQuery(typeId);
        var type = typeMealsQueryService.handle(getTypeByIdQuery);
        if (type.isEmpty()) return ResponseEntity.notFound().build();
        var typeEntity = type.get();
        var typeResource = TypeResourceFromEntityAssembler.toResourceFromEntity(typeEntity);
        return new ResponseEntity<>(typeResource, HttpStatus.CREATED);
    }

}
