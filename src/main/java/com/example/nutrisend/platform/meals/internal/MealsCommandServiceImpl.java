package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.commands.*;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.meals.jpa.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MealsCommandServiceImpl implements MealsCommandService {

    private final MealRepository mealsRepository;

    @Autowired
    public MealsCommandServiceImpl(MealRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @Override
    public Optional<Meals> handle(CreateMealsCommand command) {
        String mealId = UUID.randomUUID().toString();

        Meals meal = new Meals(command.name(), command.calories(), command.protein(),
                command.carbohydrates(), command.fats(), command.price(), command.img());

        mealsRepository.save(meal);

        return Optional.of(meal);
    }

    @Override
    public void handle(CreateCategoryMealsCommand command) {

    }

    @Override
    public void handle(CreateTypeMealsCommand command) {

    }

    @Override
    public void handle(DeleteMealsCommand command) {

    }

    @Override
    public Optional<Meals> handle(UpdateMealsCommand command) {
        return Optional.empty();
    }
}
