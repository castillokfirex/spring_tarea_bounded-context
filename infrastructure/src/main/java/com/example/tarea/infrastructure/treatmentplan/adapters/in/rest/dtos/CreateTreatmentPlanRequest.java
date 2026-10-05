package com.example.tarea.infrastructure.treatmentplan.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTreatmentPlanRequest(
        @NotNull UUID encounterId,
        @NotNull UUID professionalId,
        @NotBlank @Size(max = 200) String title,
        @NotBlank String description,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull UUID treatmentStatusId
) {
}
