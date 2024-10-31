package com.example.nutrisend.platform.meals.domain.model.commands;

import java.util.List;

public record CreateTypeMealsCommand (String name, List<CreateMealsCommand> meals) {
    public CreateTypeMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Type name cannot be null or empty");
        }
        if (meals == null || meals.isEmpty()) {
            throw new IllegalArgumentException("Meals cannot be null or empty");
        }
    }
}
