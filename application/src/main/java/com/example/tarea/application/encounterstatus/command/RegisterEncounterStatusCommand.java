package com.example.tarea.application.encounterstatus.command;

public record RegisterEncounterStatusCommand(
        String code,
        String name,
        Boolean active
) {
}
