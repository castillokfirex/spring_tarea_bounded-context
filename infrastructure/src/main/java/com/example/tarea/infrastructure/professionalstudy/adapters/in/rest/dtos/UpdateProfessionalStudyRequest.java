package com.example.tarea.infrastructure.professionalstudy.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProfessionalStudyRequest(
        @NotNull UUID studyId,
        @NotNull UUID professionalId,
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 100) String university,
        @NotNull Boolean isValid,
        @Size(max = 60) String resolutionNumber,
        @NotNull UUID countryId
) {
}
