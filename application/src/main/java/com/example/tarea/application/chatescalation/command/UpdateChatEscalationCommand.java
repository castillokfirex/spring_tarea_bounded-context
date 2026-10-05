package com.example.tarea.application.chatescalation.command;

import java.util.UUID;

import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;

public record UpdateChatEscalationCommand(
        ChatEscalationId id,
        UUID conversationId,
        UUID statusId,
        Boolean fromAi,
        String reason
) {
}
