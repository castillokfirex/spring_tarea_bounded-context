package com.example.tarea.domain.conversationstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record ConversationStatusDeletedEvent(
        ConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
