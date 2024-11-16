package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;

import java.util.Optional;

public interface MealsCommandService {
    Long handle(CreateMealsCommand command);
    void handle(DeleteMealsCommand command);
    Optional<Meals> handle(UpdateMealsCommand command);
}
