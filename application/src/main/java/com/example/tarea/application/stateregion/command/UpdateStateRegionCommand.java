package com.example.tarea.application.stateregion.command;

import java.util.UUID;

import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;

public record UpdateStateRegionCommand(
        StateRegionId id,
        String nameRegion,
        String codeRegion,
        String description,
        Boolean isActive,
        UUID countryId
) {
}
