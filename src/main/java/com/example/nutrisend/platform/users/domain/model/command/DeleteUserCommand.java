package com.example.nutrisend.platform.users.domain.model.command;

public record DeleteUserCommand(Long id) {
    public DeleteUserCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
    }
}
