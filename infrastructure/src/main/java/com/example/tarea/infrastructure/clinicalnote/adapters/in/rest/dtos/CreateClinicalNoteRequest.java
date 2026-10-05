package com.example.tarea.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateClinicalNoteRequest(
        @NotNull UUID encounterId,
        @NotNull UUID professionalId,
        @NotBlank String subjective,
        @NotBlank String objective,
        @NotBlank String assessment,
        @NotBlank String plan,
        @NotBlank String additionalNotes,
        @NotNull LocalDateTime signedAt
) {
}
