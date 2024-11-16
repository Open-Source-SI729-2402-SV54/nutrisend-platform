package com.example.nutrisend.platform.availability.application.internal;

import com.example.nutrisend.platform.availability.domain.model.aggregates.Availability;
import com.example.nutrisend.platform.availability.domain.model.commands.CreateAvailabilityCommand;
import com.example.nutrisend.platform.availability.domain.services.AvailabilityCommandService;
import com.example.nutrisend.platform.availability.infrastructure.persistence.jpa.repositories.AvailabilityRepository;
import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;

public class AvailabilityCommandServiceImpl implements AvailabilityCommandService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityCommandServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public Long handle(CreateAvailabilityCommand command) {
        if (availabilityRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Availability already exists".formatted(command.name()));
        var availability = new Availability(command);
        try {
            availabilityRepository.save(availability);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving availability: %s".formatted(e.getMessage()));
        }
        return availability.getId();
    }
}
