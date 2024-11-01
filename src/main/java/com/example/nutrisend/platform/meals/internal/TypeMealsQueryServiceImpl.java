package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.domain.model.queries.GetAllTypeMealsQuery;
import com.example.nutrisend.platform.meals.domain.model.queries.GetTypeMealsByIdQuery;
import com.example.nutrisend.platform.meals.domain.services.TypeMealsQueryService;
import com.example.nutrisend.platform.meals.jpa.TypeMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMealsQueryServiceImpl implements TypeMealsQueryService {

    private final TypeMealsRepository typeMealsRepository;

    @Autowired
    public TypeMealsQueryServiceImpl(TypeMealsRepository typeMealsRepository) {
        this.typeMealsRepository = typeMealsRepository;
    }

    @Override
    public List<TypeMeals> handle(GetAllTypeMealsQuery query) {
        return typeMealsRepository.findAll();
    }

    @Override
    public Optional<TypeMeals> handle(GetTypeMealsByIdQuery query) {
        return typeMealsRepository.findById(query.id());
    }
}
