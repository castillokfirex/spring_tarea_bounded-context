package com.example.tarea.application.chatconversationaisetting.command;

import java.util.UUID;

public record RegisterChatConversationAiSettingCommand(
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId
) {
}
