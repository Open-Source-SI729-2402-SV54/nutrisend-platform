package com.example.nutrisend.platform.users.infrastructure.persistence.jpa.respositories;

import com.example.nutrisend.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdIsNot(String name, Long id);
}
