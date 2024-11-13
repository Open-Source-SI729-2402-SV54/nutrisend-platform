package com.example.nutrisend.platform.categorymeals.application.internal;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.UpdateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsCommandService;
import com.example.nutrisend.platform.meals.application.internal.MealsCommandServiceImpl;
import com.example.nutrisend.platform.categorymeals.infrastructure.persistence.jpa.repositories.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.infrastructure.persistence.jpa.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryMealsCommandServiceImpl implements CategoryMealsCommandService {

    private final CategoryMealsRepository categoryMealsRepository;
    private final MealsCommandServiceImpl mealsCommandService;


    @Autowired
    public CategoryMealsCommandServiceImpl(CategoryMealsRepository categoryMealsRepository, MealRepository mealRepository, MealsCommandServiceImpl mealsCommandService) {
        this.categoryMealsRepository = categoryMealsRepository;
        this.mealsCommandService = mealsCommandService;
    }

    @Override
    public Optional<CategoryMeals> handle(CreateCategoryMealsCommand command) {

        String categoryID = UUID.randomUUID().toString();

        CategoryMeals categoryMeals = new CategoryMeals(command.name());

        categoryMealsRepository.save(categoryMeals);

        return Optional.of(categoryMeals);
    }

    @Override
    public void handle(DeleteCategoryMealsCommand command) {

    }

    @Override
    public Optional<CategoryMeals> handle(UpdateCategoryMealsCommand command) {
        return Optional.empty();
    }
}
