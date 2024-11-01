package com.example.nutrisend.platform.meals.domain.model.commands;

public record DeleteCategoryMealsCommand(String categoryId) {
    public DeleteCategoryMealsCommand {
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
    }
}