package com.example.nutrisend.platform.iam.interfaces.acl;

import com.example.nutrisend.platform.iam.domain.model.commands.SignUpCommand;
import com.example.nutrisend.platform.iam.domain.model.entities.Role;
import com.example.nutrisend.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.example.nutrisend.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.example.nutrisend.platform.iam.domain.services.commandservices.UserCommandService;
import com.example.nutrisend.platform.iam.domain.services.queryservices.UserQueryService;

import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class IamContextFacade {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    public Long createUser(
            String name,
            String surname,
            String email,
            String password,
            Number phone) {
        var signUpCommand = new SignUpCommand(name, surname, email,
                password, phone, List.of(Role.getDefaultRole()));
        var result = userCommandService.handle(signUpCommand);
        if(result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public Long fetchUserIdByEmail(String email){
        var getUserByEmailQuery = new GetUserByEmailQuery(email);
        var result = userQueryService.handle(getUserByEmailQuery);
        if(result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public String fetchEmailByUserId(Long userId){
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if(result.isEmpty()) return Strings.EMPTY;
        return result.get().getEmail();
    }
}
