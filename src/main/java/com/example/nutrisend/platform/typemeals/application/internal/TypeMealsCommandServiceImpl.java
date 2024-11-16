package com.example.nutrisend.platform.typemeals.application.internal;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.DeleteTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.commands.UpdateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.services.TypeMealsCommandService;
import com.example.nutrisend.platform.meals.application.internal.MealsCommandServiceImpl;
import com.example.nutrisend.platform.typemeals.infrastructure.persistence.jpa.repositories.TypeMealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TypeMealsCommandServiceImpl implements TypeMealsCommandService {

    private final TypeMealsRepository typeMealsRepository;

    @Autowired
    public TypeMealsCommandServiceImpl(TypeMealsRepository typeMealsRepository) {
        this.typeMealsRepository = typeMealsRepository;
    }

    @Override
    public Long handle(CreateTypeMealsCommand command) {
        if (typeMealsRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Type meals already exists".formatted(command.name()));
        var type = new TypeMeals(command);
        try {
            typeMealsRepository.save(type);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving type meal: %s".formatted(e.getMessage()));
        }
        return type.getId();
    }

    @Override
    public void handle(DeleteTypeMealsCommand command) {

    }

    @Override
    public Optional<TypeMeals> handle(UpdateTypeMealsCommand command) {
        return Optional.empty();
    }
}
