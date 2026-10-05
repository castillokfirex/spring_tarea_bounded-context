package com.example.tarea.domain.encounter.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;

public record EncounterDeletedEvent(
        EncounterId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
