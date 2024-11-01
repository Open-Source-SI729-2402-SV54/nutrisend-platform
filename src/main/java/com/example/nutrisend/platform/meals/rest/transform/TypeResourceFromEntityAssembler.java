package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;
import com.example.nutrisend.platform.meals.rest.resources.TypeMealResource;

import java.util.stream.Collectors;

public class TypeResourceFromEntityAssembler {
    public static TypeMealResource toResourceFromEntity(TypeMeals type) {
        return new TypeMealResource(
                type.getId().toString(),
                type.getName(),
                type.getMeals().stream()
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
