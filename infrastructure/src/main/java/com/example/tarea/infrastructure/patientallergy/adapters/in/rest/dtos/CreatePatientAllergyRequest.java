package com.example.tarea.infrastructure.patientallergy.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePatientAllergyRequest(
        @NotNull UUID patientId,
        @NotBlank @Size(max = 200) String substance,
        String reaction,
        @NotBlank @Size(max = 20) String severity,
        @NotNull Boolean active,
        LocalDateTime recordedAt,
        @NotNull UUID recordedBy
) {
}
