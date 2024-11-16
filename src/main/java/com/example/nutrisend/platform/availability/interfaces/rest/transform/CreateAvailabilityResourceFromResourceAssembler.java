package com.example.nutrisend.platform.availability.interfaces.rest.transform;

import com.example.nutrisend.platform.availability.domain.model.commands.CreateAvailabilityCommand;
import com.example.nutrisend.platform.availability.interfaces.rest.resources.CreateAvailabilityResource;

public class CreateAvailabilityResourceFromResourceAssembler {
    public static CreateAvailabilityCommand toCommandResource(CreateAvailabilityResource resource) {
        return new CreateAvailabilityCommand(
                resource.name(),
                resource.provider(),
                resource.img(),
                resource.description()
        );
    }
}
