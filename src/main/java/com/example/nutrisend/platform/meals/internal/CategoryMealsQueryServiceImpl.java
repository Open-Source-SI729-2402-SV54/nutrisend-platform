package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllCategoryMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetCategoryMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.services.CategoryMealsQueryService;
import com.example.nutrisend.platform.meals.jpa.CategoryMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryMealsQueryServiceImpl implements CategoryMealsQueryService {

    private final CategoryMealsRepository categoryMealsRepository;

    @Autowired
    public CategoryMealsQueryServiceImpl(CategoryMealsRepository mCategoryMealsRepository) {
        this.categoryMealsRepository = mCategoryMealsRepository;
    }


    @Override
    public List<CategoryMeals> handle(GetAllCategoryMealsQuery query) {
        return categoryMealsRepository.findAll();
    }

    @Override
    public Optional<CategoryMeals> handle(GetCategoryMealsByIdQuery query) {
        return categoryMealsRepository.findById(String.valueOf(query.id()));
    }


}
