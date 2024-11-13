package com.example.nutrisend.platform.typemeals.interfaces.rest.transform;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.TypeMealResource;

public class TypeResourceFromEntityAssembler {
    public static TypeMealResource toResourceFromEntity(TypeMeals type) {
        return new TypeMealResource(
                type.getId(),
                type.getName()
        );
    }
}
