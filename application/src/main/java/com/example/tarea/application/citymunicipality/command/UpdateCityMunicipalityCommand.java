package com.example.tarea.application.citymunicipality.command;

import java.util.UUID;

import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record UpdateCityMunicipalityCommand(
        CityMunicipalityId id,
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId
) {
}
