package com.example.nutrisend.platform.meals.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoryMeals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID")
    private List<Meals> meals;


    public CategoryMeals() {}

    public CategoryMeals(String name, List<Meals> meals) {
        this.name = name;
        this.meals = meals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Meals> getMeals() { return meals; }

    public void setMeals(List<Meals> meals) { this.meals = meals; }
}
