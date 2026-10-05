package com.example.tarea.domain.airunstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;

public record AiRunStatusDeletedEvent(
        AiRunStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
