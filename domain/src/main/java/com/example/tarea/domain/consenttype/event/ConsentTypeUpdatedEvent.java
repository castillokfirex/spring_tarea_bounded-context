package com.example.tarea.domain.consenttype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;

public record ConsentTypeUpdatedEvent(
        ConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
