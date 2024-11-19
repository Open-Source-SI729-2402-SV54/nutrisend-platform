package com.example.nutrisend.platform.users.domain.model.aggregates;

import com.example.nutrisend.platform.users.domain.model.command.CreateUserCommand;
import com.example.nutrisend.platform.users.domain.model.command.UpdateUserCommand;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private Long phone;

    public User(String name, String surname, String email, String password, Long phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    public User(){}

    public User(CreateUserCommand command) {
        this.name = command.name();
        this.surname = command.surname();
        this.email = command.email();
        this.password = command.password();
        this.phone = command.phone();
    }

    public User updateUser(UpdateUserCommand command) {
        this.name = command.name();
        this.surname = command.surname();
        this.email = command.email();
        this.password = command.password();
        this.phone = command.phone();
        return this;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getPhone() { return phone; }
    public void setPhone(Long phone) { this.phone = phone; }
}
