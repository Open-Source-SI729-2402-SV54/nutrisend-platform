package com.example.nutrisend.platform.users.interfaces.rest.resources;

public record UpdateUserResource(String name,
                                 String surname,
                                 String email,
                                 String password,
                                 Long phone) {
    public UpdateUserResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("surname is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is required");
        }
        if (phone == null || phone <= 0) {
            throw new IllegalArgumentException("phone is required");
        }
    }
}
