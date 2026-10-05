package com.example.tarea.infrastructure.stateregion.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateStateRegionRequest(
        @NotBlank @Size(max = 50) String nameRegion,
        @Size(max = 10) String codeRegion,
        @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @NotNull UUID countryId
) {
}
