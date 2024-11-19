package com.example.nutrisend.platform.users.domain.services;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import com.example.nutrisend.platform.users.domain.model.query.GetAllUserQuery;
import com.example.nutrisend.platform.users.domain.model.query.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUserQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
