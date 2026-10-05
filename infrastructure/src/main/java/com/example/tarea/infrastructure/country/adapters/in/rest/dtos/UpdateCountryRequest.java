package com.example.tarea.infrastructure.country.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCountryRequest(
        @NotBlank @Size(max = 50) String nameCountry,
        @Size(max = 10) String codeCountry,
        @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @Size(max = 5) String telephonePrefix
) {
}
