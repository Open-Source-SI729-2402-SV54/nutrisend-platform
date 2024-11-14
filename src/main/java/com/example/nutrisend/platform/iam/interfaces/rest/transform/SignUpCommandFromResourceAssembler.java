package com.example.nutrisend.platform.iam.interfaces.rest.transform;

import com.example.nutrisend.platform.iam.domain.model.commands.SignUpCommand;
import com.example.nutrisend.platform.iam.domain.model.entities.Role;
import com.example.nutrisend.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource){
        var roles = signUpResource.roles() != null ? signUpResource.roles().stream().map(
                name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(signUpResource.name(), signUpResource.surname(), signUpResource.email(),
                signUpResource.password(), signUpResource.phone(), roles);
    }
}
