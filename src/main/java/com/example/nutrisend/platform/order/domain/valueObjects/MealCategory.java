package com.example.nutrisend.platform.order.domain.valueObjects;

public enum MealCategory {
    BREAKFAST(1, "Breakfast"),
    LUNCH(2, "Lunch"),
    DINNER(3, "Dinner");

    private final int id;
    private final String name;

    MealCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static MealCategory fromId(int id) {
        for (MealCategory category : values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category found with ID: " + id);
    }
}