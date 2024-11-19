package com.example.nutrisend.platform.users.domain.model.command;

public record UpdateUserCommand(Long id, String name, String surname, String email, String password, Long phone) {
    public UpdateUserCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
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
