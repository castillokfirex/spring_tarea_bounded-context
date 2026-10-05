package com.example.tarea.infrastructure.assessmenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAssessmentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active,
        String description
) {
}
