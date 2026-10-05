package com.example.tarea.infrastructure.conversationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateConversationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
