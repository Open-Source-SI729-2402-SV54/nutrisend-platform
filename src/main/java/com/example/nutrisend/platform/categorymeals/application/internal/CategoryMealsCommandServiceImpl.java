package com.example.nutrisend.platform.categorymeals.application.internal;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.model.commands.UpdateCategoryMealsCommand;
import com.example.nutrisend.platform.categorymeals.domain.services.CategoryMealsCommandService;
import com.example.nutrisend.platform.meals.application.internal.MealsCommandServiceImpl;
import com.example.nutrisend.platform.categorymeals.infrastructure.persistence.jpa.repositories.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.infrastructure.persistence.jpa.repositories.MealRepository;
import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryMealsCommandServiceImpl implements CategoryMealsCommandService {

    private final CategoryMealsRepository categoryMealsRepository;


    @Autowired
    public CategoryMealsCommandServiceImpl(CategoryMealsRepository categoryMealsRepository) {
        this.categoryMealsRepository = categoryMealsRepository;
    }

    @Override
    public Long handle(CreateCategoryMealsCommand command) {
        if (categoryMealsRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Type meals already exists".formatted(command.name()));
        var category = new CategoryMeals(command);
        try {
            categoryMealsRepository.save(category);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving category meal: %s".formatted(e.getMessage()));
        }
        return category.getId();
    }

    @Override
    public void handle(DeleteCategoryMealsCommand command) {
        if (!categoryMealsRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Type meals does not exists".formatted(command.id()));
        }
        try {
            categoryMealsRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting category meal: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<CategoryMeals> handle(UpdateCategoryMealsCommand command) {
        if (categoryMealsRepository.existsByNameAndIdIsNot(command.name(), command.id()))
            throw new IllegalArgumentException("Type meals already exists".formatted(command.name()));
        var result = categoryMealsRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("Type meals does not exists".formatted(command.id()));
        var categoryToUpdate = result.get();
        try {
            var updatedCategory = categoryMealsRepository.save(categoryToUpdate.updateCategory(command.name()));
            return Optional.of(updatedCategory);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating category meal: %s".formatted(e.getMessage()));
        }
    }
}
