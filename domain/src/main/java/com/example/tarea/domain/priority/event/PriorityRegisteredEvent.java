package com.example.tarea.domain.priority.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;

public record PriorityRegisteredEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
