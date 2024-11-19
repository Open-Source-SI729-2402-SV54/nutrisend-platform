package com.example.nutrisend.platform.users.interfaces.rest.resources;

public record UserResource(
        Long id,
        String name,
        String surname,
        String email,
        String password,
        Long phone
) {
}
