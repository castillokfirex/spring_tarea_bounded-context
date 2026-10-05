package com.example.tarea.application.medicationroute.command;

import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;

public record UpdateMedicationRouteCommand(
        MedicationRouteId id,
        String code,
        String name,
        Boolean active
) {
}
