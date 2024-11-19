package com.example.nutrisend.platform.iam.domain.model.commands;

import com.example.nutrisend.platform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String name,
        String surname,
        String email,
        String password,
        String phone,
        List<Role> roles
) {
}
