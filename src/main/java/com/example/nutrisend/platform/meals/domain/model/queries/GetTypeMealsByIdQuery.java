package com.example.nutrisend.platform.meals.domain.model.queries;

public record GetTypeMealsByIdQuery(String id) {
    public GetTypeMealsByIdQuery {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }
}
