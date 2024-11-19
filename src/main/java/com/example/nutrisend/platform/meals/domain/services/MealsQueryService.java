package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllMealsQuery;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetCategoryMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetMealsByIdQuery;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetTypeMealsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MealsQueryService {
    List<Meals> handle(GetAllMealsQuery query);
    Optional<Meals> handle(GetCategoryMealsByIdQuery query);
    Optional<Meals> handle(GetTypeMealsByIdQuery query);
    Optional<Meals> handle(GetMealsByIdQuery query);
}
