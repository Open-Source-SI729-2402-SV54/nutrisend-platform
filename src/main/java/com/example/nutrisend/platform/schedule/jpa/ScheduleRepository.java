package com.example.nutrisend.platform.schedule.jpa;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
