package com.example.tarea.infrastructure.escalationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEscalationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
