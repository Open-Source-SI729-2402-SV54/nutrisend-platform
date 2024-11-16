package com.example.nutrisend.platform.categorymeals.interfaces.rest.resources;

public record UpdateCategoryResource(String name) {
    public UpdateCategoryResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }
}
