package com.example.nutrisend.platform.users.application.internal;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import com.example.nutrisend.platform.users.domain.model.query.GetAllUserQuery;
import com.example.nutrisend.platform.users.domain.model.query.GetUserByIdQuery;
import com.example.nutrisend.platform.users.domain.services.UserQueryService;
import com.example.nutrisend.platform.users.infrastructure.persistence.jpa.respositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUserQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }
}
