package com.example.tarea.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateChatEscalationRequest(
        @NotNull UUID conversationId,
        @NotNull UUID statusId,
        @NotNull Boolean fromAi,
        @NotBlank String reason
) {
}
