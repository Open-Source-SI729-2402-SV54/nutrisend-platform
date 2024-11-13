package com.example.nutrisend.platform.typemeals.domain.model.queries;

public record GetTypeMealsByIdQuery(Long id) {
    public GetTypeMealsByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to zero");
        }
    }
}
