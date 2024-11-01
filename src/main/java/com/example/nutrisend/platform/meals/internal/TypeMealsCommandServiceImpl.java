package com.example.nutrisend.platform.meals.internal;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateTypeMealsCommand;
import com.example.nutrisend.platform.meals.domain.services.TypeMealsCommandService;
import com.example.nutrisend.platform.meals.jpa.TypeMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TypeMealsCommandServiceImpl implements TypeMealsCommandService {

    private final TypeMealsRepository typeMealsRepository;
    private final MealsCommandServiceImpl mealsCommandService;

    @Autowired
    public TypeMealsCommandServiceImpl(TypeMealsRepository typeMealsRepository, MealsCommandServiceImpl mealsCommandService) {
        this.typeMealsRepository = typeMealsRepository;
        this.mealsCommandService = mealsCommandService;
    }

    @Override
    public Optional<TypeMeals> handle(CreateTypeMealsCommand command) {

        String typeID = UUID.randomUUID().toString();

        TypeMeals typeMeals = new TypeMeals(command.name());

        typeMealsRepository.save(typeMeals);

        return Optional.of(typeMeals);
    }

    @Override
    public void handle(DeleteTypeMealsCommand command) {

    }

    @Override
    public Optional<TypeMeals> handle(UpdateTypeMealsCommand command) {
        return Optional.empty();
    }
}
