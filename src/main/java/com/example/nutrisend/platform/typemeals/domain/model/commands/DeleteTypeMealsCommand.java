package com.example.nutrisend.platform.typemeals.domain.model.commands;

public record DeleteTypeMealsCommand(String typeId) {
    public DeleteTypeMealsCommand {
        if (typeId == null || typeId.isBlank()) {
            throw new IllegalArgumentException("Type ID cannot be null or empty");
        }
    }
}