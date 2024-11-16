package com.example.nutrisend.platform.meals.application.internal;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.categorymeals.infrastructure.persistence.jpa.repositories.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.infrastructure.persistence.jpa.repositories.MealRepository;
import com.example.nutrisend.platform.typemeals.infrastructure.persistence.jpa.repositories.TypeMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MealsCommandServiceImpl implements MealsCommandService {

    private final MealRepository mealsRepository;
    private final CategoryMealsRepository categoryMealsRepository;
    private final TypeMealsRepository typeMealsRepository;

    @Autowired
    public MealsCommandServiceImpl(MealRepository mealsRepository, CategoryMealsRepository categoryMealsRepository, TypeMealsRepository typeMealsRepository) {
        this.mealsRepository = mealsRepository;
        this.categoryMealsRepository = categoryMealsRepository;
        this.typeMealsRepository = typeMealsRepository;
    }

    @Override
    public Long handle(CreateMealsCommand command) {
        if (mealsRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Meal already exists with name: ".formatted(command.name()));
        }
        var meals = new Meals(command, categoryMealsRepository, typeMealsRepository);
        try {
            mealsRepository.save(meals);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving meal: " + e.getMessage());
        }
        return meals.getId();
    }


    @Override
    public void handle(DeleteMealsCommand command) {

    }

    @Override
    public Optional<Meals> handle(UpdateMealsCommand command) {
        return Optional.empty();
    }
}
