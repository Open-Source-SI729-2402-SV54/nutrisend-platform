package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.services.CategoryMealsCommandService;
import com.example.nutrisend.platform.meals.jpa.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.jpa.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
