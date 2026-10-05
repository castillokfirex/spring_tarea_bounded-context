package com.example.tarea.application.stateregion.command;

import java.util.UUID;

public record RegisterStateRegionCommand(
        String nameRegion,
        String codeRegion,
        String description,
        Boolean isActive,
        UUID countryId
) {
}
