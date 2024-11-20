package com.example.nutrisend.platform.notification.interfaces.rest.transform;

import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationResourceFromResourceAssembler {



    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.userId(),
                resource.email(),
                resource.message(),
                resource.typeId(),
                resource.active(),
                resource.notificationTime()
        );
    }
}