package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllCategoryMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetCategoryMealsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryMealsQueryService {
    List<CategoryMeals> handle(GetAllCategoryMealsQuery query);
    Optional<CategoryMeals> handle(GetCategoryMealsByIdQuery query);
}
