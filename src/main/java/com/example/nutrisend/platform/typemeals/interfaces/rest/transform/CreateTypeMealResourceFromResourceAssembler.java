package com.example.nutrisend.platform.typemeals.interfaces.rest.transform;

import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.CreateTypeMealResource;

public class CreateTypeMealResourceFromResourceAssembler {
    public static CreateTypeMealsCommand toCommandFromResource(CreateTypeMealResource resource) {
        return new CreateTypeMealsCommand(resource.name());
    }
}
