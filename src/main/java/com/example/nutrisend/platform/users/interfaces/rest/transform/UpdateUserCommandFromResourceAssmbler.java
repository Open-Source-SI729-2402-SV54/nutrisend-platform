package com.example.nutrisend.platform.users.interfaces.rest.transform;

import com.example.nutrisend.platform.users.domain.model.command.UpdateUserCommand;
import com.example.nutrisend.platform.users.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssmbler {

    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(id, resource.name(), resource.surname(), resource.email(), resource.password(), resource.phone());
    }
}
