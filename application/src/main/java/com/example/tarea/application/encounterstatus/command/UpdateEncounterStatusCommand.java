package com.example.tarea.application.encounterstatus.command;

import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record UpdateEncounterStatusCommand(
        EncounterStatusId id,
        String code,
        String name,
        Boolean active
) {
}
