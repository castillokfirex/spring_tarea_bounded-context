package com.example.tarea.domain.messagetype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;

public record MessageTypeDeletedEvent(
        MessageTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
