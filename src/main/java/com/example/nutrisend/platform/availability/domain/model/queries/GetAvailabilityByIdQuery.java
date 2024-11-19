package com.example.nutrisend.platform.availability.domain.model.queries;

public record GetAvailabilityByIdQuery (Long id) {
    public GetAvailabilityByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to zero");
        }
    }
}
