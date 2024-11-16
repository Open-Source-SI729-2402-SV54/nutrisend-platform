package com.example.nutrisend.platform.typemeals.domain.model.commands;

public record CreateTypeMealsCommand (String name) {
    public CreateTypeMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Type name cannot be null or empty");
        }
    }
}
