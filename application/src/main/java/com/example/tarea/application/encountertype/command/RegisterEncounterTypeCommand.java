package com.example.tarea.application.encountertype.command;

public record RegisterEncounterTypeCommand(
        String code,
        String name,
        Boolean active
) {
}
