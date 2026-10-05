package com.example.tarea.application.chatmessage.command;

import java.util.UUID;

import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;

public record UpdateChatMessageCommand(
        ChatMessageId id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata
) {
}
