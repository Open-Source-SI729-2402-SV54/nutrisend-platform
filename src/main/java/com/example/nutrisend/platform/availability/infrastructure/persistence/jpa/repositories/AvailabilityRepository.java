package com.example.nutrisend.platform.availability.infrastructure.persistence.jpa.repositories;

import com.example.nutrisend.platform.availability.domain.model.aggregates.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
}
