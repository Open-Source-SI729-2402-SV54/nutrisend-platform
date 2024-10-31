package com.example.nutrisend.platform.meals.domain.model.commands;

import java.util.List;

public record CreateCategoryMealsCommand (String name, List<CreateMealsCommand> meals)
{
    public CreateCategoryMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        if (meals == null || meals.isEmpty()) {
            throw new IllegalArgumentException("Meals cannot be null or empty");
        }

    }
}
