package com.example.tarea.domain.chatescalation.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;

public record ChatEscalationRegisteredEvent(
        ChatEscalationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
