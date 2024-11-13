package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.services.MealsCommandService;
import com.example.nutrisend.platform.categorymeals.jpa.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.jpa.MealRepository;
import com.example.nutrisend.platform.typemeals.jpa.TypeMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MealsCommandServiceImpl implements MealsCommandService {

    private final MealRepository mealsRepository;
    private final TypeMealsRepository typeMealsRepository;
    private final CategoryMealsRepository categoryMealsRepository;

    @Autowired
    public MealsCommandServiceImpl(MealRepository mealsRepository, TypeMealsRepository typeMealsRepository, CategoryMealsRepository categoryMealsRepository) {
        this.mealsRepository = mealsRepository;
        this.typeMealsRepository = typeMealsRepository;
        this.categoryMealsRepository = categoryMealsRepository;
    }

    @Override
    public Optional<Meals> handle(CreateMealsCommand command) {
        String mealId = UUID.randomUUID().toString();

        TypeMeals type = typeMealsRepository.findById(command.typeID())
                .orElseThrow(() -> new IllegalArgumentException("TypeMeals not found for ID: " + command.typeID()));

        CategoryMeals category = categoryMealsRepository.findById(command.categoryID())
                .orElseThrow(() -> new IllegalArgumentException("CategoryMeals not found for ID: " + command.categoryID()));

        Meals meal = new Meals(type, category, command.name(), command.calories(), command.protein(), command.carbohydrates(), command.fats(), command.price(), command.img() );
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
