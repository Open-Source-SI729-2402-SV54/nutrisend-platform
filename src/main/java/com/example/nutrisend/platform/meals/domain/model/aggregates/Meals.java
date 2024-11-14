package com.example.nutrisend.platform.meals.domain.model.aggregates;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.categorymeals.infrastructure.persistence.jpa.repositories.CategoryMealsRepository;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateMealsCommand;
import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import com.example.nutrisend.platform.typemeals.domain.model.commands.CreateTypeMealsCommand;
import com.example.nutrisend.platform.typemeals.infrastructure.persistence.jpa.repositories.TypeMealsRepository;
import jakarta.persistence.*;

@Entity
public class Meals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double calories;

    @Column(nullable = false)
    private Double protein;

    @Column(nullable = false)
    private Double carbohydrates;

    @Column(nullable = false)
    private Double fats;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String img;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryMeals category;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeMeals type;


    public Meals() {}

    public Meals(CategoryMeals category, TypeMeals type, String name, Double calories,
                 Double protein, Double carbohydrates, Double fats, Double price, String img) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.price = price;
        this.img = img;
    }


    public Meals(CreateMealsCommand command, CategoryMealsRepository categoryMealsRepository, TypeMealsRepository typeMealsRepository) {
        CategoryMeals category = categoryMealsRepository.findById(command.categoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        TypeMeals type = typeMealsRepository.findById(command.typeID())
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));

        this.category = category;
        this.type = type;
        this.name = command.name();
        this.calories = command.calories();
        this.protein = command.protein();
        this.carbohydrates = command.carbohydrates();
        this.fats = command.fats();
        this.price = command.price();
        this.img = command.img();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryMeals getCategory() { return category; }
    public void setCategory(CategoryMeals category) { this.category = category; }

    public TypeMeals getType() { return type; }
    public void setType(TypeMeals type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getCalories() { return calories; }
    public void setCalories(Double calories) { this.calories = calories; }

    public Double getProtein() { return protein; }
    public void setProtein(Double protein) { this.protein = protein; }

    public Double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(Double carbohydrates) { this.carbohydrates = carbohydrates; }

    public Double getFats() { return fats; }
    public void setFats(Double fats) { this.fats = fats; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

}
