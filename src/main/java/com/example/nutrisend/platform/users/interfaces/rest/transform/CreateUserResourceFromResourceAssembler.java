package com.example.nutrisend.platform.users.interfaces.rest.transform;

import com.example.nutrisend.platform.users.domain.model.command.CreateUserCommand;
import com.example.nutrisend.platform.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserResourceFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.name(),
                resource.surname(),
                resource.email(),
                resource.password(),
                resource.phone());
    }
}
