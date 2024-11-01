package com.example.nutrisend.platform.meals.domain.model.commands;

import java.util.List;

public record UpdateCategoryMealsCommand(String categoryId, String name, List<CreateMealsCommand> meals) {
    public UpdateCategoryMealsCommand {
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}