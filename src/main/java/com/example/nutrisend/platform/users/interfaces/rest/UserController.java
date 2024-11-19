package com.example.nutrisend.platform.users.interfaces.rest;

import com.example.nutrisend.platform.users.domain.model.command.DeleteUserCommand;
import com.example.nutrisend.platform.users.domain.model.query.GetAllUserQuery;
import com.example.nutrisend.platform.users.domain.model.query.GetUserByIdQuery;
import com.example.nutrisend.platform.users.domain.services.UserCommandService;
import com.example.nutrisend.platform.users.domain.services.UserQueryService;
import com.example.nutrisend.platform.users.interfaces.rest.resources.CreateUserResource;
import com.example.nutrisend.platform.users.interfaces.rest.resources.UpdateUserResource;
import com.example.nutrisend.platform.users.interfaces.rest.resources.UserResource;
import com.example.nutrisend.platform.users.interfaces.rest.transform.CreateUserResourceFromResourceAssembler;
import com.example.nutrisend.platform.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssmbler;
import com.example.nutrisend.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
@Tag(name = "Users", description = "Available User Endpoints")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users")
    @ApiResponses( value =  {
        @ApiResponse(responseCode = "200", description = "Users found"),
        @ApiResponse(responseCode = "404", description = "Users not found")
    })

    public ResponseEntity<List<UserResource>> getAllUser() {
        var users = userQueryService.handle(new GetAllUserQuery());
        if (users.isEmpty()) return ResponseEntity.notFound().build();
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by id", description = "Get User by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> getUserById(@PathVariable("id") Long id) {
        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        var userEntity = user.get();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(userEntity);
        return ResponseEntity.ok(userResource);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserResourceFromResourceAssembler.toCommandFromResource(resource);
        var userId = userCommandService.handle(createUserCommand);
        if (userId == null || userId == 0L) return ResponseEntity.badRequest().build();
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        var userEntity = user.get();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(userEntity);
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> updateUser(@PathVariable("id") Long id,
                                                                   @RequestBody UpdateUserResource resource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssmbler.toCommandFromResource(id, resource);
        var updatedUser = userCommandService.handle(updateUserCommand);
        if (updatedUser.isEmpty()) return ResponseEntity.notFound().build();
        var updatedUserEntity = updatedUser.get();
        var updatedUserResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUserEntity);
        return ResponseEntity.ok(updatedUserResource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Delete User")
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        var deleteUserCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.ok("Deleted user");
    }




}
