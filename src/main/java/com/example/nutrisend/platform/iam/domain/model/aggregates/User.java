package com.example.nutrisend.platform.iam.domain.model.aggregates;


import com.example.nutrisend.platform.iam.domain.model.commands.SignUpCommand;
import com.example.nutrisend.platform.iam.domain.model.entities.Role;
import com.example.nutrisend.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 15)
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    // Default constructor
    public User() {
        this.roles = new HashSet<>();
    }

    // Constructor for creating a User with basic attributes
    public User(String name, String surname, String email, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roles = new HashSet<>();
    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }
    // Constructor for creating a User with roles
    public User(String name, String surname, String email, String password, String phone, List<Role> roles) {
        this(name, surname, email, password, phone);
        addRoles(roles);
    }

    // Methods for managing roles
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }


}
