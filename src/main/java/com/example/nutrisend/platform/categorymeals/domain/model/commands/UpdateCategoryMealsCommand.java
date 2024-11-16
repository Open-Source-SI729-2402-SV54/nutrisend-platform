package com.example.nutrisend.platform.categorymeals.domain.model.commands;

import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;

import java.util.List;

public record UpdateCategoryMealsCommand(Long id, String name) {
    public UpdateCategoryMealsCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}