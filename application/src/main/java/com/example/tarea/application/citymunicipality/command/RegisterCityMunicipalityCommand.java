package com.example.tarea.application.citymunicipality.command;

import java.util.UUID;

public record RegisterCityMunicipalityCommand(
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId
) {
}
