package com.example.nutrisend.platform.users.interfaces.rest.resources;

public record CreateUserResource(
        String name,
        String surname,
        String email,
        String password,
        Long phone
) {
}
