package com.example.tarea.infrastructure.chatairunerror.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatAiRunErrorRequest(
        @NotNull UUID aiRunId,
        @NotBlank String errorMessage,
        @NotBlank @Size(max = 80) String errorCode,
        @NotBlank @Size(max = 120) String providerErrorId
) {
}
