package com.example.tarea.infrastructure.professionaltype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProfessionalTypeRequest(
        @NotBlank @Size(max = 40) String name
) {
}
