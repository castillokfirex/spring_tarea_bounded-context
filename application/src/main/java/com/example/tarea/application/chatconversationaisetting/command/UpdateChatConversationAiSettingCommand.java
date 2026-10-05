package com.example.tarea.application.chatconversationaisetting.command;

import java.util.UUID;

import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record UpdateChatConversationAiSettingCommand(
        ChatConversationAiSettingId id,
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId
) {
}
