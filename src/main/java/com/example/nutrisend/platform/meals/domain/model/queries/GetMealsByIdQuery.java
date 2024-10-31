package com.example.nutrisend.platform.meals.domain.model.queries;

public record GetMealsByIdQuery(String id) {
    public GetMealsByIdQuery {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }
}
