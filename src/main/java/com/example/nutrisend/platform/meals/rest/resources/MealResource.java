package com.example.nutrisend.platform.meals.rest.resources;

public record MealResource(
        String categoryID,
        String typeID,
        Long id,
        String name,
        double calories,
        double protein,
        double carbohydrates,
        double fats,
        double price,
        String img
) {
}
