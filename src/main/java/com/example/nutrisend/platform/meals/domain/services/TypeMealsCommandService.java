package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateTypeMealsCommand;

import java.util.Optional;

public interface TypeMealsCommandService {
    Optional<TypeMeals> handle(CreateTypeMealsCommand command);
    void handle(DeleteTypeMealsCommand command);
    Optional<TypeMeals> handle(UpdateTypeMealsCommand command);
}
