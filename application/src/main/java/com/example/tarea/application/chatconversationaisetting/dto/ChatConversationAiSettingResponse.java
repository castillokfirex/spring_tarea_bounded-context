package com.example.tarea.application.chatconversationaisetting.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;

public record ChatConversationAiSettingResponse(
        UUID id,
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ChatConversationAiSettingResponse fromDomain(ChatConversationAiSetting aggregate) {
        return new ChatConversationAiSettingResponse(
                aggregate.id().value(),
                aggregate.conversationId(),
                aggregate.aiEnabled(),
                aggregate.defaultModelId(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
