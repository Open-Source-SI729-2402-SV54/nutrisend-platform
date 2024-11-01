package com.example.nutrisend.platform.meals.domain.model.commands;

import java.util.List;

public record CreateCategoryMealsCommand (String name)
{
    public CreateCategoryMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}
