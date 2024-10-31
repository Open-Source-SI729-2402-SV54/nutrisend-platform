package com.example.nutrisend.platform.meals.jpa;

import com.example.nutrisend.platform.meals.domain.model.aggregates.TypeMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMealsRepository extends JpaRepository<TypeMeals, String> {
}
