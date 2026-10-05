package com.example.tarea.application.chatairun.command;

import java.util.UUID;

public record RegisterChatAiRunCommand(
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId
) {
}
