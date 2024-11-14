package com.example.nutrisend.platform.meals.interfaces.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.interfaces.rest.resources.CreateMealResource;

public class CreateMealResourceFromResourceAssembler {
    public static CreateMealsCommand toCommandFromResource(CreateMealResource resource) {
        return new CreateMealsCommand(
                resource.categoryID(),
                resource.typeID(),
                resource.name(),
                resource.calories(),
                resource.protein(),
                resource.carbohydrates(),
                resource.fats(),
                resource.price(),
                resource.img()
        );
    }
}
