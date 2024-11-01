package com.example.nutrisend.platform.schedule.internal;

import com.example.nutrisend.platform.schedule.domain.model.aggregates.Schedule;
import com.example.nutrisend.platform.schedule.domain.model.commands.CreateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.DeleteScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.model.commands.UpdateScheduleCommand;
import com.example.nutrisend.platform.schedule.domain.services.ScheduleService;
import com.example.nutrisend.platform.schedule.jpa.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Schedule schedule = new Schedule(command.userId(), command.days());
        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<Schedule> updateSchedule(UpdateScheduleCommand command) {
        return scheduleRepository.findById(command.userId().getId()).map(schedule -> {
            schedule.setDays(command.days());
            return scheduleRepository.save(schedule);
        });
    }

    @Override
    public boolean deleteSchedule(DeleteScheduleCommand command) {
        Optional<Schedule> schedule = scheduleRepository.findById(command.userId().getId());
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