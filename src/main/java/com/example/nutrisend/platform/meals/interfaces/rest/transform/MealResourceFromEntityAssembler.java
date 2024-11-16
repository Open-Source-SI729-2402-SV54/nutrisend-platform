package com.example.nutrisend.platform.meals.interfaces.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.interfaces.rest.resources.MealResource;

public class MealResourceFromEntityAssembler {
    public static MealResource toResourceFromEntity(Meals meal) {
        return new MealResource(
                meal.getId(),
                meal.getCategory().getId(),
                meal.getType().getId(),
                meal.getName(),
                meal.getCalories(),
                meal.getProtein(),
                meal.getCarbohydrates(),
                meal.getFats(),
                meal.getPrice(),
                meal.getImg()
        );
    }

}
