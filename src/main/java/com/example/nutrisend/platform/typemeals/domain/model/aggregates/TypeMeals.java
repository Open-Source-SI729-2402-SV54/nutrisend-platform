package com.example.nutrisend.platform.typemeals.domain.model.aggregates;

import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import jakarta.persistence.*;

@Entity
public class TypeMeals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    public TypeMeals() {}

    public TypeMeals(String name) {
        this.name = name;
    }

    public TypeMeals(CreateTypeMealsCommand command) {
        this.name = command.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
