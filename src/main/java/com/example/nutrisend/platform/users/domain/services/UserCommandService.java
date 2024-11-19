package com.example.nutrisend.platform.users.domain.services;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import com.example.nutrisend.platform.users.domain.model.command.CreateUserCommand;
import com.example.nutrisend.platform.users.domain.model.command.DeleteUserCommand;
import com.example.nutrisend.platform.users.domain.model.command.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
}
