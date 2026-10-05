package com.example.tarea.application.encountermodality.command;

public record RegisterEncounterModalityCommand(
        String code,
        String name,
        Boolean active
) {
}
