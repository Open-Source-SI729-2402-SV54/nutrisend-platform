package com.example.nutrisend.platform.categorymeals.domain.model.commands;

public record CreateCategoryMealsCommand (String name)
{
    public CreateCategoryMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}
