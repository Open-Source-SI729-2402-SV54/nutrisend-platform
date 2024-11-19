package com.example.nutrisend.platform.meals.domain.model.commands;

public record CreateMealsCommand(
        Long categoryID,  // Nuevo campo
        Long typeID,
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
        if (protein <= 0) {
            throw new IllegalArgumentException("Protein must be greater than zero");
        }
        if (carbohydrates <= 0) {
            throw new IllegalArgumentException("Carbohydrates must be greater than zero");
        }
        if (fats <= 0) {
            throw new IllegalArgumentException("Fats must be greater than zero");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (img == null || img.isBlank()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
        if (categoryID == null || categoryID <= 0) {
            throw new IllegalArgumentException("Category ID cannot be null or must be greater than zero");
        }
        if (typeID == null || typeID <= 0) {
            throw new IllegalArgumentException("Type ID cannot be null or must be greater than zero");
        }
    }
}
