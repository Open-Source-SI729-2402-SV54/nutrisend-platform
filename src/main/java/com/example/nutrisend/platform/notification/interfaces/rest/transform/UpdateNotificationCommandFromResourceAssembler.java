package com.example.nutrisend.platform.notification.interfaces.rest.transform;

import com.example.nutrisend.platform.notification.domain.model.commands.UpdateNotificationCommand;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {

    public static UpdateNotificationCommand toCommandFromResource(Long id,
                                                                  UpdateNotificationResource resource) {
        return new UpdateNotificationCommand(
                id,
                resource.userId(),
                resource.email(),
                resource.message(),
                resource.typeId(),
                resource.active(),
                resource.notificationTime());
    }
}
