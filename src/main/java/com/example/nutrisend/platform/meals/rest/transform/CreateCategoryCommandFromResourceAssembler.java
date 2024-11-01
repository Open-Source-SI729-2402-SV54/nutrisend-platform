package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.rest.resources.CreateCategoryMealResource;

import java.util.List;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryMealsCommand toCommandFromResource(CreateCategoryMealResource resource) {
        List<CreateMealsCommand> meals = resource.meal()
                .map(meal -> List.of(CreateMealCommandFromResourceAssembler.toCommandFromResource(meal)))
                .orElse(List.of());

        return new CreateCategoryMealsCommand(resource.name(), meals);
    }
}
