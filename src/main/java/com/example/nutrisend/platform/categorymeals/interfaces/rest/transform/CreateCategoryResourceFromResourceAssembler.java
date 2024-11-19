package com.example.nutrisend.platform.categorymeals.interfaces.rest.transform;

import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.resources.CreateCategoryMealResource;

public class CreateCategoryResourceFromResourceAssembler {
    public static CreateCategoryMealsCommand toCommandFromResource(CreateCategoryMealResource resource) {
        return new CreateCategoryMealsCommand(resource.name());
    }
}
