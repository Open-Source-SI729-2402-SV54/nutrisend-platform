package com.example.nutrisend.platform.meals.rest.resources;



public record CreateMealResource(
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
