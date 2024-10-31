package com.example.nutrisend.platform.meals.domain.model.commands;

public record CreateMealsCommand(
        String name,
        double calories,
        double protein,
        double carbohydrates,
        double fats,
        double price,
        String img
) {
    public CreateMealsCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Meal name cannot be null or empty");
        }
        if (calories <= 0) {
            throw new IllegalArgumentException("Calories must be greater than zero");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (img == null || img.isBlank()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }
    }
}
