package com.example.tarea.infrastructure.gender.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateGenderRequest(
        @NotBlank @Size(max = 50) String description
) {
}
