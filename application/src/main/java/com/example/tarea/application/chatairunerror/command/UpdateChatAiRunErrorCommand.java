package com.example.tarea.application.chatairunerror.command;

import java.util.UUID;

import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public record UpdateChatAiRunErrorCommand(
        ChatAiRunErrorId id,
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {
}
