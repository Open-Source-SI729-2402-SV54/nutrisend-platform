package com.example.nutrisend.platform.meals.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
