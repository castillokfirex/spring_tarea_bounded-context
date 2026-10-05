package com.example.tarea.application.chatairunerror.command;

import java.util.UUID;

public record RegisterChatAiRunErrorCommand(
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {
}
