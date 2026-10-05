package com.example.tarea.application.chatescalationstatushistory.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public record UpdateChatEscalationStatusHistoryCommand(
        ChatEscalationStatusHistoryId id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime changedAt
) {
}
