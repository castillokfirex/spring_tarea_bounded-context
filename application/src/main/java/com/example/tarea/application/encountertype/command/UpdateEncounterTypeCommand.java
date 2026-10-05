package com.example.tarea.application.encountertype.command;

import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;

public record UpdateEncounterTypeCommand(
        EncounterTypeId id,
        String code,
        String name,
        Boolean active
) {
}
