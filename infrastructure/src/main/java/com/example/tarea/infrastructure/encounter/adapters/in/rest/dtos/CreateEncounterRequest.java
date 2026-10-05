package com.example.tarea.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEncounterRequest(
        @NotNull UUID clinicalRecordId,
        @NotNull UUID professionalId,
        @NotNull UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        @NotBlank String reasonForVisit,
        @NotBlank String currentCondition,
        @NotNull UUID modalityId,
        @NotNull UUID statusId,
        @NotNull UUID createdBy,
        @NotNull UUID updatedBy
) {
}
