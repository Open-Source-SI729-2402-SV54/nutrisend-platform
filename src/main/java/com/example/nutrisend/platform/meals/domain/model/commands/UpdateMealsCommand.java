package com.example.nutrisend.platform.meals.domain.model.commands;

public record UpdateMealsCommand(
        String mealId,
        String name,
        Double calories,
        Double protein,
        Double carbohydrates,
        Double fats,
        Double price,
        String img
) {
    public UpdateMealsCommand {
        if (mealId == null) {
            throw new IllegalArgumentException("Meal ID cannot be null");
        }
    }
}