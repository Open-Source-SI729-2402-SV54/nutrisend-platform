package com.example.nutrisend.platform.availability.domain.model.commands;

public record CreateAvailabilityCommand (String name, String provider, String img, String description) {
    public CreateAvailabilityCommand {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be blank");
        if (provider == null || provider.isBlank()) throw new IllegalArgumentException("provider cannot be blank");
        if (img == null || img.isBlank()) throw new IllegalArgumentException("img cannot be blank");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description cannot be blank");
    }
}
