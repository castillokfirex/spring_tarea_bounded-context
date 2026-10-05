package com.example.tarea.application.chatmessage.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;

public record ChatMessageResponse(
        UUID id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata,
        LocalDateTime createdAt) {

    public static ChatMessageResponse fromDomain(ChatMessage aggregate) {
        return new ChatMessageResponse(
                aggregate.id().value(),
                aggregate.conversationId(),
                aggregate.messageTypeId(),
                aggregate.participantId(),
                aggregate.content(),
                aggregate.metadata(),
                aggregate.createdAt());
    }
}
