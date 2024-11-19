package com.example.nutrisend.platform.schedule.rest.resources;

import com.example.nutrisend.platform.meals.interfaces.rest.resources.MealResource;

public record DailyMealResource(
        MealResource breakfast,
        MealResource lunch,
        MealResource dinner
) {
}
