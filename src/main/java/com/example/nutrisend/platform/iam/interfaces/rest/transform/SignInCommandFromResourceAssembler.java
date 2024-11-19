package com.example.nutrisend.platform.iam.interfaces.rest.transform;

import com.example.nutrisend.platform.iam.domain.model.commands.SignInCommand;
import com.example.nutrisend.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

    public static SignInCommand toCommandFromResource(SignInResource signInResource){
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
