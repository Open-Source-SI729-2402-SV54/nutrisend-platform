package com.example.nutrisend.platform.iam.domain.services;

import com.example.nutrisend.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
