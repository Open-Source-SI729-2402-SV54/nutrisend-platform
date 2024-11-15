package com.example.nutrisend.platform.categorymeals.domain.model.commands;

public record DeleteCategoryMealsCommand(Long id) {
    public DeleteCategoryMealsCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
    }
}