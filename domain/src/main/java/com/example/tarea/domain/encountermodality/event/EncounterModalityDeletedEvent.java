package com.example.tarea.domain.encountermodality.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;

public record EncounterModalityDeletedEvent(
        EncounterModalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
