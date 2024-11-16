package com.example.nutrisend.platform.typemeals.domain.model.commands;


import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;

import java.util.List;

public record UpdateTypeMealsCommand(String typeId, String name, List<CreateMealsCommand> meals) {
    public UpdateTypeMealsCommand {
        if (typeId == null || typeId.isBlank()) {
            throw new IllegalArgumentException("Type ID cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Type name cannot be null or empty");
        }
    }
}