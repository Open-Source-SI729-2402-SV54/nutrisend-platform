package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.rest.resources.MealResource;

public class MealResourceFromEntityAssembler {
    public static MealResource toResourceFromEntity(Meals meal) {
        return new MealResource(
                meal.getCategory() != null ? meal.getCategory().getId().toString() : null,  // categoryID
                meal.getType() != null ? meal.getType().getId().toString() : null,            // typeID
                meal.getId(),
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
