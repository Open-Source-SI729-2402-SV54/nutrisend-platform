package com.example.nutrisend.platform.availability.domain.services;

import com.example.nutrisend.platform.availability.domain.model.commands.CreateAvailabilityCommand;

public interface AvailabilityCommandService {
    Long handle(CreateAvailabilityCommand command);
}
