package com.example.nutrisend.platform.meals.domain.model.commands;

public record UpdateMealsCommand(
        String name,
        Double calories,
        Double protein,
        Double carbohydrates,
        Double fats,
        Double price,
        String img,
        String categoryID,  // Nuevo campo
        String typeID
) {
    public UpdateMealsCommand {
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
        if (categoryID == null || categoryID.isBlank()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        if (typeID == null || typeID.isBlank()) {
            throw new IllegalArgumentException("Type ID cannot be null or empty");
        }
    }
}