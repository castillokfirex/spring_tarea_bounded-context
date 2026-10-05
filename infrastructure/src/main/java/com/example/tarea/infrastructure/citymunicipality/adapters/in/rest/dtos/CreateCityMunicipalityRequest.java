package com.example.tarea.infrastructure.citymunicipality.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCityMunicipalityRequest(
        @NotBlank @Size(max = 50) String nameCity,
        @Size(max = 10) String codeCity,
        @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @NotNull UUID regionId
) {
}
