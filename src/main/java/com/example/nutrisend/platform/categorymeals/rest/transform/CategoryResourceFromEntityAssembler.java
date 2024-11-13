package com.example.nutrisend.platform.categorymeals.rest.transform;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.rest.resources.CategoryMealResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryMealResource toResourceFromEntity(CategoryMeals category) {
        return new CategoryMealResource(
                category.getId(),
                category.getName()
        );
    }
}
