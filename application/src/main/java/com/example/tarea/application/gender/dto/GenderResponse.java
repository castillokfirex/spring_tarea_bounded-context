package com.example.tarea.application.gender.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.gender.model.aggregate.Gender;

public record GenderResponse(
        UUID id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static GenderResponse fromDomain(Gender aggregate) {
        return new GenderResponse(
                aggregate.id().value(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
