package com.example.nutrisend.platform.typemeals.domain.services;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetTypeMealsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TypeMealsQueryService {
    List<TypeMeals> handle(GetAllTypeMealsQuery query);
    Optional<TypeMeals> handle(GetTypeMealsByIdQuery query);
}
