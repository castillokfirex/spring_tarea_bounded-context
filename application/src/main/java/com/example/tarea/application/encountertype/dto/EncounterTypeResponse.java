package com.example.tarea.application.encountertype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;

public record EncounterTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EncounterTypeResponse fromDomain(EncounterType aggregate) {
        return new EncounterTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
