package com.example.tarea.application.treatmentstatus.command;

public record RegisterTreatmentStatusCommand(
        String code,
        String name,
        Boolean active
) {
}
