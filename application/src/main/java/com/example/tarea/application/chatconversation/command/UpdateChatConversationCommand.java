package com.example.tarea.application.chatconversation.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;

public record UpdateChatConversationCommand(
        ChatConversationId id,
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy
) {
}
