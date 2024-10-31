package com.example.nutrisend.platform.meals.domain.model.queries;

public record GetCategoryMealsByIdQuery(String id) {
    public GetCategoryMealsByIdQuery {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }
}
