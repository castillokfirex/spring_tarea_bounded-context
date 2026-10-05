package com.example.tarea.domain.documenttype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.documenttype.model.valueobject.DocumentTypeId;

public record DocumentTypeDeletedEvent(
        DocumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
