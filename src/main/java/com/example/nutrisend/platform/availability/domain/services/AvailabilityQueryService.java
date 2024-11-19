package com.example.nutrisend.platform.availability.domain.services;

import com.example.nutrisend.platform.availability.domain.model.aggregates.Availability;
import com.example.nutrisend.platform.availability.domain.model.queries.GetAllAvailabilityQuery;
import com.example.nutrisend.platform.availability.domain.model.queries.GetAvailabilityByIdQuery;
import java.util.List;
import java.util.Optional;

public interface AvailabilityQueryService {
    List<Availability> handle(GetAllAvailabilityQuery query);
    Optional<Availability> handle(GetAvailabilityByIdQuery query);
}
