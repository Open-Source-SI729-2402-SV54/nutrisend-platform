package com.example.nutrisend.platform.categorymeals.domain.model.aggregates;

import com.example.nutrisend.platform.categorymeals.domain.model.commands.CreateCategoryMealsCommand;
import jakarta.persistence.*;

@Entity
public class CategoryMeals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    public CategoryMeals() {}

    public CategoryMeals(String name) {
        this.name = name;
    }

    public CategoryMeals(CreateCategoryMealsCommand command) {
        this.name = command.name();
    }

    public CategoryMeals updateCategory(String name) {
        this.name = name;
        return this;
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
