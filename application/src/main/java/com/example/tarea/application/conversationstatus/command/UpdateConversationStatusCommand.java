package com.example.tarea.application.conversationstatus.command;

import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record UpdateConversationStatusCommand(
        ConversationStatusId id,
        String nameStatus
) {
}
