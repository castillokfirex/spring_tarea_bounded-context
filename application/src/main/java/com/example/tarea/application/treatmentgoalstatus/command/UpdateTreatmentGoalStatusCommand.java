package com.example.tarea.application.treatmentgoalstatus.command;

import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record UpdateTreatmentGoalStatusCommand(
        TreatmentGoalStatusId id,
        String code,
        String name,
        Boolean active
) {
}
