package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.rest.resources.CategoryMealResource;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;

import java.util.stream.Collectors;

public class CategoryResourceFromEntityAssembler {
    public static CategoryMealResource toResourceFromEntity(CategoryMeals category) {
        return new CategoryMealResource(
                category.getId().toString(),
                category.getName(),
                category.getMeals().stream()
                        .map(meal -> new CreateMealResource(
                                meal.getType() != null ? meal.getType().toString() : "",
                                meal.getCategory() != null ? meal.getCategory().toString() : "",
                                meal.getName(),
                                meal.getCalories(),
                                meal.getProtein(),
                                meal.getCarbohydrates(),
                                meal.getFats(),
                                meal.getPrice(),
                                meal.getImg() != null ? meal.getImg() : ""
                        ))
                        .collect(Collectors.toList())
        );
    }
}
