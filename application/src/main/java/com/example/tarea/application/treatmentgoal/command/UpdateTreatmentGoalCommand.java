package com.example.tarea.application.treatmentgoal.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public record UpdateTreatmentGoalCommand(
        TreatmentGoalId id,
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalId
) {
}
