package com.example.nutrisend.platform.availability.interfaces.rest.resources;

public record AvailabilityResource(
        Long id,
        String name,
        String provider,
        String img,
        String description
) {
}
