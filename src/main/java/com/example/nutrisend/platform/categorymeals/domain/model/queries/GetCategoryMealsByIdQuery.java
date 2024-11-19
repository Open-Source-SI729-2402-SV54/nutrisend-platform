package com.example.nutrisend.platform.categorymeals.domain.model.queries;

public record GetCategoryMealsByIdQuery(Long id) {
    public GetCategoryMealsByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to zero");
        }
    }
}
