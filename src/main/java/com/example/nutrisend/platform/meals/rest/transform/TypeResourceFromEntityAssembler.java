package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.rest.resources.CreateMealResource;
import com.example.nutrisend.platform.meals.rest.resources.TypeMealResource;

import java.util.stream.Collectors;

public class TypeResourceFromEntityAssembler {
    public static TypeMealResource toResourceFromEntity(TypeMeals type) {
        return new TypeMealResource(
                type.getId().toString(),
                type.getName()
        );
    }
}
