package com.example.tarea.domain.escalationstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record EscalationStatusDeletedEvent(
        EscalationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
