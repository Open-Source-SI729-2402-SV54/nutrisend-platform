package com.example.nutrisend.platform.availability.application.internal;

import com.example.nutrisend.platform.availability.domain.model.aggregates.Availability;
import com.example.nutrisend.platform.availability.domain.model.queries.GetAllAvailabilityQuery;
import com.example.nutrisend.platform.availability.domain.model.queries.GetAvailabilityByIdQuery;
import com.example.nutrisend.platform.availability.domain.services.AvailabilityQueryService;
import com.example.nutrisend.platform.availability.infrastructure.persistence.jpa.repositories.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityQueryServiceImpl implements AvailabilityQueryService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityQueryServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public List<Availability> handle(GetAllAvailabilityQuery query) {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> handle(GetAvailabilityByIdQuery query) {
        return availabilityRepository.findById(query.id());
    }
}
