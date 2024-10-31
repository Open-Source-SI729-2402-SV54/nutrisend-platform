package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.commands.*;

import java.util.Optional;

public interface MealsCommandService {
    Optional<Meals> handle(CreateMealsCommand command);
    void handle(CreateCategoryMealsCommand command);
    void handle(CreateTypeMealsCommand command);
    void handle(DeleteMealsCommand command);
    Optional<Meals> handle(UpdateMealsCommand command);
}
