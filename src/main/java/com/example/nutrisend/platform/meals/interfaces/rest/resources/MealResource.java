package com.example.nutrisend.platform.meals.interfaces.rest.resources;

public record MealResource(
        Long id,
        Long typeID,
        Long categoryID,
        String name,
        double calories,
        double protein,
        double carbohydrates,
        double fats,
        double price,
        String img
) {
}
