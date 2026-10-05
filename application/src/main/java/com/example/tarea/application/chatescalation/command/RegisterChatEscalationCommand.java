package com.example.tarea.application.chatescalation.command;

import java.util.UUID;

public record RegisterChatEscalationCommand(
        UUID conversationId,
        UUID statusId,
        Boolean fromAi,
        String reason
) {
}
