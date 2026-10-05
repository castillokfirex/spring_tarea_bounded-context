package com.example.tarea.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateChatMessageRequest(
        @NotNull UUID conversationId,
        @NotNull UUID messageTypeId,
        @NotNull UUID participantId,
        @NotBlank String content,
        @NotBlank String metadata
) {
}
