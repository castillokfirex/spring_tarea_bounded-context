package com.example.tarea.application.medicationroute.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.medicationroute.model.aggregate.MedicationRoute;

public record MedicationRouteResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static MedicationRouteResponse fromDomain(MedicationRoute aggregate) {
        return new MedicationRouteResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
