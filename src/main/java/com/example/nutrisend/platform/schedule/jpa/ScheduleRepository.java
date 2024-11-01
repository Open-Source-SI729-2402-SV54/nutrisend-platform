package com.example.nutrisend.platform.schedule.jpa;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByUserId(Long userId);
}
