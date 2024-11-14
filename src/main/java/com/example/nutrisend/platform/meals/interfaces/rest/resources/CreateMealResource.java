package com.example.nutrisend.platform.meals.interfaces.rest.resources;



public record CreateMealResource(
        Long categoryID,
        Long typeID,
        String name,
        double calories,
        double protein,
        double carbohydrates,
        double fats,
        double price,
        String img
) {
}
