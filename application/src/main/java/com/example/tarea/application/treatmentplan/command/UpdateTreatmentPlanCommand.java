package com.example.tarea.application.treatmentplan.command;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public record UpdateTreatmentPlanCommand(
        TreatmentPlanId id,
        UUID encounterId,
        UUID professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        UUID treatmentStatusId
) {
}
