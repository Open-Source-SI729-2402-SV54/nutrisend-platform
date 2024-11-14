package com.example.nutrisend.platform.typemeals.domain.services;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.DeleteTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.UpdateTypeMealsCommand;

import java.util.Optional;

public interface TypeMealsCommandService {
    Long handle(CreateTypeMealsCommand command);
    void handle(DeleteTypeMealsCommand command);
    Optional<TypeMeals> handle(UpdateTypeMealsCommand command);
}
