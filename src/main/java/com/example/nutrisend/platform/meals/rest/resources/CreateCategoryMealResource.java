package com.example.nutrisend.platform.meals.rest.resources;

import java.util.Optional;

public record CreateCategoryMealResource(
        String id,
        String name,
        Optional<CreateMealResource> meal
) {
}
