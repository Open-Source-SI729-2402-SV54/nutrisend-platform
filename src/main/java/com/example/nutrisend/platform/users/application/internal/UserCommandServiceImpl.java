package com.example.nutrisend.platform.users.application.internal;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import com.example.nutrisend.platform.users.domain.model.command.CreateUserCommand;
import com.example.nutrisend.platform.users.domain.model.command.DeleteUserCommand;
import com.example.nutrisend.platform.users.domain.model.command.UpdateUserCommand;
import com.example.nutrisend.platform.users.domain.services.UserCommandService;
import com.example.nutrisend.platform.users.infrastructure.persistence.jpa.respositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Username already exists".formatted(command.name()));
        var user = new User(command);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving category meal: %s".formatted(e.getMessage()));
        }
        return user.getId();
    }

    @Override
    public void handle(DeleteUserCommand command) {
        if(!userRepository.existsById(command.id())) {
            throw new IllegalArgumentException("User not found");
        }
        try {
            userRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting user: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        if (userRepository.existsByNameAndIdIsNot(command.name(), command.id()))
            throw new IllegalArgumentException("Username already exists".formatted(command.name()));
        var result = userRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("User not found");
        var userToUpdate = result.get();
        try {
            var updatedUser = userRepository.save(userToUpdate.updateUser(command));
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating user: %s".formatted(e.getMessage()));
        }
    }
}
