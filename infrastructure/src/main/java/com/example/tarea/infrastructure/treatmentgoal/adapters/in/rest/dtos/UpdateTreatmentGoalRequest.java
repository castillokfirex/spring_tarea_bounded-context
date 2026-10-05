package com.example.tarea.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTreatmentGoalRequest(
        @NotNull UUID treatmentPlanId,
        @NotBlank String description,
        @NotNull LocalDate targetDate,
        LocalDateTime completedAt,
        @NotBlank String notes,
        @NotNull UUID treatmentGoalId
) {
}
