package com.example.nutrisend.platform.meals.domain.model.queries;

public record GetMealsByIdQuery(Long id) {
    public GetMealsByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or zero");
        }
    }
}
