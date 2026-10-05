package com.example.tarea.infrastructure.airunstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAiRunStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
