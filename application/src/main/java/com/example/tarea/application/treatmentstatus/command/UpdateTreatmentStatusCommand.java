package com.example.tarea.application.treatmentstatus.command;

import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record UpdateTreatmentStatusCommand(
        TreatmentStatusId id,
        String code,
        String name,
        Boolean active
) {
}
