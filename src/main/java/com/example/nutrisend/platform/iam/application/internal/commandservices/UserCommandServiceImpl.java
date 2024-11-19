package com.example.nutrisend.platform.iam.application.internal.commandservices;


import com.example.nutrisend.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.example.nutrisend.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.example.nutrisend.platform.iam.domain.model.aggregates.User;
import com.example.nutrisend.platform.iam.domain.model.commands.SignInCommand;
import com.example.nutrisend.platform.iam.domain.model.commands.SignUpCommand;
import com.example.nutrisend.platform.iam.domain.services.UserCommandService;
import com.example.nutrisend.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.example.nutrisend.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command){
        if(userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Email already exists");
        var roles = command.roles().stream().map(
                role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Roles name not found"))
        ).toList();
        var user = new User(command.name(), command.surname(),
                command.email(), hashingService.encode(command.password()),
                command.name(), roles);
        userRepository.save(user);
        return userRepository.findByEmail(command.email());
    }


    public Optional<ImmutablePair<User, String>> handle(SignInCommand command){
        var user = userRepository.findByEmail(command.email());
        if(user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

}
