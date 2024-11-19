package com.example.nutrisend.platform.users.domain.model.command;

public record CreateUserCommand (String name, String surname, String email, String password, Long phone) {
    public CreateUserCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("User surname cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("User password cannot be null or empty");
        }
        if (phone == null || phone <= 0) {
            throw new IllegalArgumentException("User phone cannot be null or empty");
        }
    }
}
