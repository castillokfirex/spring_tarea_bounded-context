package com.example.tarea.infrastructure.gender.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateGenderRequest(
        @NotBlank @Size(max = 50) String description
) {
}
