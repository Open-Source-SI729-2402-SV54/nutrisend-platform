package com.example.nutrisend.platform.schedule.internal;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleService;
import com.example.nutrisend.platform.schedule.jpa.ScheduleRepository;
import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule createSchedule(CreateScheduleCommand command) {
        // Implementa la lógica de creación si es necesario
        return null;
    }

    @Override
    public Optional<Schedule> updateSchedule(UpdateScheduleCommand command) {
        return scheduleRepository.findById(command.userId()).map(schedule -> {
            // Aquí transformamos el Map<String, Map<String, Long>> a Map<String, Long>
            Map<String, Long> transformedDays = new HashMap<>();
            command.days().forEach((day, meals) -> {
                // Suponiendo que tomamos solo un valor del Map<String, Long>
                // Puedes ajustar la lógica según sea necesario
                Long mealId = meals.values().stream().findFirst().orElse(null);
                transformedDays.put(day, mealId);
            });

            schedule.setDays(transformedDays); // Ahora se establece el Map<String, Long>
            return scheduleRepository.save(schedule);
        });
    }

    @Override
    public boolean deleteSchedule(DeleteScheduleCommand command) {
        Optional<Schedule> schedule = scheduleRepository.findById(command.userId());
        if (schedule.isPresent()) {
            scheduleRepository.delete(schedule.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Schedule> getScheduleByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }
}