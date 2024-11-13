package com.example.nutrisend.platform.categorymeals.rest.transform;

import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.rest.resources.CreateCategoryMealResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryMealsCommand toCommandFromResource(CreateCategoryMealResource resource) {
        return new CreateCategoryMealsCommand(resource.name());
    }
}
