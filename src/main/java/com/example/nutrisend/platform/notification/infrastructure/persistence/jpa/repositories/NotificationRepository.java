package com.example.nutrisend.platform.notification.infrastructure.persistence.jpa.repositories;

import com.example.nutrisend.platform.notification.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    Optional<Notification> findById(Long id);

    List<Notification> findByActive(Boolean active);

    boolean existsByTypeIdAndIdIsNot(Long typeId, Long id);
}
