package com.example.nutrisend.platform.meals.domain.model.commands;

public record DeleteMealsCommand(String mealId) {
    public DeleteMealsCommand {
        if (mealId == null) {
            throw new IllegalArgumentException("Meal ID cannot be null");
        }
    }
}