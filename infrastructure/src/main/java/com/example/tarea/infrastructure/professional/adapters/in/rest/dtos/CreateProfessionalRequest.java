package com.example.tarea.infrastructure.professional.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProfessionalRequest(
        @NotNull UUID documentTypeId,
        @NotBlank @Size(max = 30) String documentNumber,
        @NotBlank @Size(max = 60) String firstName,
        @NotBlank @Size(max = 60) String lastName,
        @NotNull UUID professionalType,
        @NotBlank @Size(max = 100) String licenseNumber,
        @NotNull Boolean active,
        @NotNull UUID cityId
) {
}
