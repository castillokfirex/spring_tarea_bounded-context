package com.example.tarea.infrastructure.priority.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePriorityRequest(
        @NotBlank @Size(max = 50) String namePriority
) {
}
