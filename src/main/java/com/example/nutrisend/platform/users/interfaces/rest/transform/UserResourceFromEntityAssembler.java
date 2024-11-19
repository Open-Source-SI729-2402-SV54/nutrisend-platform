package com.example.nutrisend.platform.users.interfaces.rest.transform;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import com.example.nutrisend.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhone());
    }
}
