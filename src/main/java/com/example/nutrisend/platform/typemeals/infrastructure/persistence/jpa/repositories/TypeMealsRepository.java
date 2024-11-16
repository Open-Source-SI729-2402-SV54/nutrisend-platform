package com.example.nutrisend.platform.typemeals.infrastructure.persistence.jpa.repositories;

import com.example.nutrisend.platform.typemeals.domain.model.aggregates.TypeMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMealsRepository extends JpaRepository<TypeMeals, Long> {

    boolean existsByName(String name);

}
