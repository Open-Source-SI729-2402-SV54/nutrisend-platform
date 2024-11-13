package com.example.nutrisend.platform.typemeals.interfaces.rest.transform;

import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.interfaces.rest.resources.CreateTypeMealResource;

public class CreateTypeMealCommandFromResourceAssembler {
    public static CreateTypeMealsCommand toCommandFromResource(CreateTypeMealResource resource) {
        return new CreateTypeMealsCommand(resource.name());
    }
}
