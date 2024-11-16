package com.example.nutrisend.platform.categorymeals.infrastructure.persistence.jpa.repositories;

import com.example.nutrisend.platform.categorymeals.domain.model.aggregates.CategoryMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMealsRepository extends JpaRepository<CategoryMeals, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdIsNot(String name, Long id);
}
