package com.example.nutrisend.platform.availability.interfaces.rest.resources;

public record CreateAvailabilityResource(
        String name,
        String provider,
        String img,
        String description
) {
}
