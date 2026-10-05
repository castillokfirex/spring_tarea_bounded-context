package com.example.tarea.domain.contact.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.contact.model.valueobject.ContactId;

public record ContactUpdatedEvent(
        ContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
