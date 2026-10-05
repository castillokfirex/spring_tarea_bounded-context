package com.example.tarea.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatConversationAiSettingRequest(
        @NotNull UUID conversationId,
        @NotNull Boolean aiEnabled,
        @NotNull UUID defaultModelId
) {
}
