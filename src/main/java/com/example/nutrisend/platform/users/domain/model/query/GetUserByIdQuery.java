package com.example.nutrisend.platform.users.domain.model.query;

public record GetUserByIdQuery(Long id) {
    public GetUserByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to zero");
        }
    }
}
