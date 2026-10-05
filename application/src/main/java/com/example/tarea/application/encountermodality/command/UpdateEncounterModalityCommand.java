package com.example.tarea.application.encountermodality.command;

import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;

public record UpdateEncounterModalityCommand(
        EncounterModalityId id,
        String code,
        String name,
        Boolean active
) {
}
