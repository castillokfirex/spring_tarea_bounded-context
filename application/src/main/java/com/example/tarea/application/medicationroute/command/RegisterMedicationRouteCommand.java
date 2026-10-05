package com.example.tarea.application.medicationroute.command;

public record RegisterMedicationRouteCommand(
        String code,
        String name,
        Boolean active
) {
}
