package com.example.tarea.domain.encountertype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;

public record EncounterTypeUpdatedEvent(
        EncounterTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
