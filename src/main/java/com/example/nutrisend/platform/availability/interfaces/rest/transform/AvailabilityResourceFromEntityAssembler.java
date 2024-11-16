package com.example.nutrisend.platform.availability.interfaces.rest.transform;

import com.example.nutrisend.platform.availability.domain.model.aggregates.Availability;
import com.example.nutrisend.platform.availability.interfaces.rest.resources.AvailabilityResource;

public class AvailabilityResourceFromEntityAssembler {
    public static AvailabilityResource toResourceFromEntity(Availability availability) {
        return new AvailabilityResource(
                availability.getId(),
                availability.getName(),
                availability.getProvider(),
                availability.getImg(),
                availability.getDescription()
        );
    }
}
