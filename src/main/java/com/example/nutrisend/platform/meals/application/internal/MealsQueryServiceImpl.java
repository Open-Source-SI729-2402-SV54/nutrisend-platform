package com.example.nutrisend.platform.meals.application.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllMealsQuery;
import com.example.nutrisend.platform.categorymeals.domain.model.queries.GetCategoryMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetMealsByIdQuery;
import com.example.nutrisend.platform.typemeals.domain.model.queries.GetTypeMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.services.MealsQueryService;
import com.example.nutrisend.platform.meals.infrastructure.persistence.jpa.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealsQueryServiceImpl implements MealsQueryService {

    private final MealRepository mealRepository;

    @Autowired
    public MealsQueryServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @Override
    public List<Meals> handle(GetAllMealsQuery query) {
        return mealRepository.findAll();
    }

    @Override
    public Optional<Meals> handle(GetCategoryMealsByIdQuery query) {
        return mealRepository.findById(query.id());
    }

    @Override
    public Optional<Meals> handle(GetTypeMealsByIdQuery query) {
        return mealRepository.findById(query.id());
    }

    @Override
    public Optional<Meals> handle(GetMealsByIdQuery query) {
        return mealRepository.findById(query.id());
    }
}
