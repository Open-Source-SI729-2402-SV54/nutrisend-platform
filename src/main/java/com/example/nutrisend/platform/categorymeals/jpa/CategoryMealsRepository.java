package com.example.nutrisend.platform.categorymeals.jpa;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMealsRepository extends JpaRepository<CategoryMeals, Long> {
}
