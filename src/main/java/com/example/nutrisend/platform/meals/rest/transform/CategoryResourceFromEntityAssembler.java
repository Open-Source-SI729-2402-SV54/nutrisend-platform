package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.rest.resources.CategoryMealResource;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;

import java.util.stream.Collectors;

public class CategoryResourceFromEntityAssembler {
    public static CategoryMealResource toResourceFromEntity(CategoryMeals category) {
        return new CategoryMealResource(
                category.getId().toString(),
                category.getName()
        );
    }
}
