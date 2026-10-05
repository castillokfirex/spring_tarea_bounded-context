package com.example.tarea.application.citymunicipality.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;

public record CityMunicipalityResponse(
        UUID id,
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static CityMunicipalityResponse fromDomain(CityMunicipality aggregate) {
        return new CityMunicipalityResponse(
                aggregate.id().value(),
                aggregate.nameCity(),
                aggregate.codeCity(),
                aggregate.description(),
                aggregate.isActive(),
                aggregate.regionId(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
