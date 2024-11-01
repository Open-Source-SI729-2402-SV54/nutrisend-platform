package com.example.nutrisend.platform.meals.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.meals.rest.resources.CreateTypeMealResource;

import java.util.List;

public class CreateTypeMealCommandFromResourceAssembler {
    public static CreateTypeMealsCommand toCommandFromResource(CreateTypeMealResource resource) {
        return new CreateTypeMealsCommand(resource.name());
    }
}
