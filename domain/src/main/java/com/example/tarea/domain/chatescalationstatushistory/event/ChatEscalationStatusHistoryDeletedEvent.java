package com.example.tarea.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public record ChatEscalationStatusHistoryDeletedEvent(
        ChatEscalationStatusHistoryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
