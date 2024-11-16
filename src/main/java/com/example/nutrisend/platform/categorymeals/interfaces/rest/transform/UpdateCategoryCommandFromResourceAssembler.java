package com.example.nutrisend.platform.categorymeals.interfaces.rest.transform;

import com.example.nutrisend.platform.categorymeals.domain.model.commands.UpdateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.interfaces.rest.resources.UpdateCategoryResource;

public class UpdateCategoryCommandFromResourceAssembler {

    public static UpdateCategoryMealsCommand toCommandFromResource(Long id,
                                                                   UpdateCategoryResource resource) {
        return new UpdateCategoryMealsCommand(id, resource.name());
    }
}
