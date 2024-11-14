package com.example.nutrisend.platform.categorymeals.domain.services;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.UpdateCategoryMealsCommand;

import java.util.Optional;

public interface CategoryMealsCommandService {
    Long handle(CreateCategoryMealsCommand command);
    void handle(DeleteCategoryMealsCommand command);
    Optional<CategoryMeals> handle(UpdateCategoryMealsCommand command);
}
