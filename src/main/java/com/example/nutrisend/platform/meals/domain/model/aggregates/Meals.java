package com.example.nutrisend.platform.meals.domain.model.aggregates;

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

    public Meals() {}

    public Meals(String name, Double calories, Double protein,
                 Double carbohydrates, Double fats, Double price,
                 String img) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.price = price;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
