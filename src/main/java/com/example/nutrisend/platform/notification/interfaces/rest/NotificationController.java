package com.example.nutrisend.platform.notification.interfaces.rest;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import com.example.nutrisend.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.example.nutrisend.platform.notification.domain.model.queries.GetAllNotificationsQuery;
import com.example.nutrisend.platform.notification.domain.model.queries.GetNotificationByIdQuery;
import com.example.nutrisend.platform.notification.domain.services.NotificationCommandService;
import com.example.nutrisend.platform.notification.domain.services.NotificationQueryService;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.CreateNotificationResource;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.NotificationResource;
import com.example.nutrisend.platform.notification.interfaces.rest.resources.UpdateNotificationResource;
import com.example.nutrisend.platform.notification.interfaces.rest.transform.CreateNotificationResourceFromResourceAssembler;
import com.example.nutrisend.platform.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.example.nutrisend.platform.notification.interfaces.rest.transform.UpdateNotificationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/notifications",  produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Available Notifications Endpoints")
public class NotificationController {
    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;

    public NotificationController(NotificationQueryService notificationQueryService, NotificationCommandService notificationCommandService) {
        this.notificationQueryService = notificationQueryService;
        this.notificationCommandService = notificationCommandService;
    }

    // Get /api/v1/notifications
    @GetMapping
    @Operation(summary = "Get all notifications", description = "Get all notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications found"),
            @ApiResponse(responseCode = "404", description = "No notifications found")
    })
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var notifications = notificationQueryService.handle(new GetAllNotificationsQuery());
        if (notifications.isEmpty()) return ResponseEntity.notFound().build();
        var notificationResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationResources);
    }

    // Get /api/v1/notifications/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get Notification by id", description = "Get notification by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification found"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable("id") Long id) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(id);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.notFound().build();
        var notificationEntity = notification.get();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notificationEntity);
        return ResponseEntity.ok(notificationResource);
    }

    // Post /api/v1/notifications
    @PostMapping
    @Operation(summary = "Create a new notification", description = "Create a new notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource) {
        var createNotificationCommand = CreateNotificationResourceFromResourceAssembler.toCommandFromResource(resource);
        var notificationId = notificationCommandService.handle(createNotificationCommand);
        if (notificationId == null || notificationId == 0L) return ResponseEntity.badRequest().build();
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.notFound().build();
        var notificationEntity = notification.get();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notificationEntity);
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification", description = "Update notification")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Notification updated"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<NotificationResource> updateNotification(@PathVariable("id") Long id, @RequestBody UpdateNotificationResource resource) {
        var updateNotificationCommand = UpdateNotificationCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updateNotification = notificationCommandService.handle(updateNotificationCommand);
        if (updateNotification.isEmpty()) return ResponseEntity.notFound().build();
        var updatedNotificationEntity = updateNotification.get();
        var updatedNotificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(updatedNotificationEntity);
        return ResponseEntity.ok(updatedNotificationResource);
    }


}